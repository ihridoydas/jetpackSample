
ImageDb create for AR Image

### Find Augmented Image Tool
```
//For Mac OS
https://github.com/google-ar/arcore-android-sdk/tree/master/tools/arcoreimg/macos

//For Windows
https://github.com/google-ar/arcore-android-sdk/tree/master/tools/arcoreimg/windows

```
### Need to sudu
```
sudo chmod +x "arcoreimg"

```

```
./arcoreimg build-db --input_images_directory="/Users/hridoydas/Desktop/images" --output_db_path="/Users/hridoydas/Desktop/imagedb.imgdb"

```

if in (mac Os get error) need to get permission [Privecy and Sceurity] to permession

-> Click Apple icon in top
-> System Settings
-> click [Privecy and Sceurity]
-> In Security-> need not allow arcoreimg [allow anyway] 


Some info

####  From Local
```

    //From Local
            //If Get From app resourse like (matrix.mp4)
            mediaPlayer = MediaPlayer.create(getApplication(), R.raw.matrix).apply {
                setSurface(externalTexture.surface)
                isLooping = true
            }

```

#### From Url

```

     //From Url
            val url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4" // your URL here
            //If Get From app resourse like (matrix.mp4)
            mediaPlayer = MediaPlayer.create(getApplication(), Uri.parse(url)).apply {
                setSurface(externalTexture.surface)
                isLooping = true
            }

```