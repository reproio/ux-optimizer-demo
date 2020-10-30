# remote-config-demo

## How to build the demo application.
1. Install Android Studio `3.4` or later.
2. Clone this repository.
3. Open this project in Android Studio.
4. Android Studio should synchronize automatically with gradle, but you may need to synchronize manully with `File > Sync Project with Gradle File`.
5. Then please select the `app` by build configuration.

<img width="600" alt="" src="https://user-images.githubusercontent.com/25496478/67455522-cd207980-f668-11e9-9613-5748d428266f.png">
6. You can build the demo app with `Run`.

<img width="600" alt="" src="https://user-images.githubusercontent.com/25496478/67455523-ce51a680-f668-11e9-9997-7180aec39c14.png">


7. You may validate application runtime logs in the `Logcat` panel of the debug area.

## Setup
1. Please set your Repro SDK Token at `MainActivity.kt > setupRepro() -> setup('<YOUR_REPRO_SDK_TOKEN>')`.
2. You also may change the Repro SDK Version at `app > build.gradle > dependencies` to use the latest SDK.

## UX-Optimizer Parameters

|Key|  Type |  Example  |
|---|---|---|
|title|  string  |  Tシャツ  |
|message|  string  | 夏に着るおしゃれアイテム！ |
|title_color|  string  | 333333 |
|image_url|  string, null  | https://example.com/sample.jpg |
|spring_mode|  string  | true |
|button_position|  string, A or B  | A |

## Change Theme
Spring theme is applied if `spring_mode` is set to `"true"`.
If you enable Spring Mode, you cannot change `title_color`.

| Normal Mode |  Spring Mode  |
|---|---|
|<img width="170" alt="normal mode" src="https://user-images.githubusercontent.com/25496478/74325667-5f8d8a00-4dcc-11ea-93cc-e70e99ccffa7.png">|<img width="170" alt="spring mode" src="https://user-images.githubusercontent.com/25496478/74325677-61efe400-4dcc-11ea-96b1-3efcef70da04.png">|

| Button Position A | Button Position B |
|---|---|
|<img width="170" alt="button position A" src="https://user-images.githubusercontent.com/25496478/74325661-5d2b3000-4dcc-11ea-945a-b142105b467f.png">|<img width="170" alt="button position B" src="https://user-images.githubusercontent.com/25496478/74325663-5e5c5d00-4dcc-11ea-9e97-c4fc306d21e3.png">|





