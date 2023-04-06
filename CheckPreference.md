### How to check preference Datastore

# 環境構築（Mac での環境構築）
1. proto-lensのインストール
2. テキスト化する方法

## 1. proto-lensのインストール手順

1. Homebrewインストール済みであれば、以下コマンドを実行
   `brew install protobuf`

### 参考URL
https://google.github.io/proto-lens/installing-protoc.html

## 2. テキスト化する方法

1. デバッグビルドしたアプリをインストールした端末の以下ディレクトリにあるDataStore設定ファイルをローカル環境に保存する
   `/data/data/com.ihridoydas.simpleapp.develop/files/datastore/appPref.preferences_pb`
2. 以下コマンドを実行するとテキスト化される
   `protoc --decode_raw < appPref.preferences_pb`