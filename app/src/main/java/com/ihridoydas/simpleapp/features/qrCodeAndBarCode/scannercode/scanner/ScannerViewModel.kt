package com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ihridoydas.simpleapp.data.local.PrefDataStore
import com.ihridoydas.simpleapp.features.qrCodeAndBarCode.scannercode.data.ScanRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ScannerViewModel @Inject constructor(
    private val scanRepository: ScanRepository,
    private val prefDataStore: PrefDataStore
): ViewModel() {

    private val vmState = MutableStateFlow(ScannerUiState())
    private var _scannedValues =  MutableStateFlow(emptyMap<Int, String>())
    val scannedValues = _scannedValues.asStateFlow()

    private var inputValue =  MutableStateFlow("")

    private val _scanValue = MutableLiveData(5)
    var scanValue: LiveData<Int> = _scanValue
    lateinit var predefinedKeys: List<Int>

    val uiState = vmState.stateIn(
        viewModelScope,
        SharingStarted.Eagerly,
        vmState.value,
    )

    init{
        viewModelScope.launch {
            prefDataStore.getScanValue.collect {
                _scanValue.value = it
                println("Checkin:${_scanValue.value}")
                // Predefined keys (1 to 5)
                 predefinedKeys  = (1.._scanValue.value!!).toList()
            }
        }
    }

    init {
        viewModelScope.launch {
            scanRepository.getLatestScan().collectLatest { scan ->
                inputValue.value = scan.displayValue
                // Check if the value already exists in _scannedValues
                val valueExists = _scannedValues.value.values.contains(scan.displayValue)
                if (scan.displayValue.isNotEmpty() && !valueExists) {
                    val nextKey = predefinedKeys.firstOrNull { key -> key !in _scannedValues.value.keys }
                    if (nextKey != null) {
                        // Associate the value with the next predefined key
                        _scannedValues.value = _scannedValues.value + (nextKey to scan.displayValue)
                        if (_scannedValues.value.size == predefinedKeys.size) {
                            println("Check: ${_scanValue.value}")
                            println("Check: ${_scannedValues.value.size}")
                            println("Check: ${_scannedValues.value.keys}:${_scannedValues.value.values}")
                            scanRepository.pauseScan()
                            vmState.update { it.copy( scan = scan, showBottomSheet = true ) }
                        }
                    }
                }
            }
        }
    }

    fun onEvent(event: ScannerEvent) {
        when(event) {
            is ScannerEvent.CreatePreviewView -> {
                viewModelScope.launch {
                    val previewView = scanRepository.getCameraPreview(event.lifecycleOwner)
                    vmState.update { it.copy( previewView = previewView ) }
                }
            }
            ScannerEvent.BottomSheetShown -> {
                viewModelScope.launch { scanRepository.pauseScan() }
                vmState.update { it.copy( showBottomSheet = false ) }
            }
            ScannerEvent.BottomSheetHidden -> {
                viewModelScope.launch { scanRepository.resumeScan() }
                _scannedValues.value = emptyMap()
                inputValue.value = ""
            }
            is ScannerEvent.CameraRequiredDialogVisibility -> {
                vmState.update { it.copy( showCameraRequiredDialog = event.show ) }
            }
        }
    }

    suspend fun saveScanCode(scanValue: Int) {
        prefDataStore.setScanValue(scanValue)
    }
}