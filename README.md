# remote-config-demo

## How to build application.
1. Install Android Studio `3.4` ~.
2. Pull this repository.
3. Open this project root with Android Studio.
4. Android Studio will synchronize automatically gradle. If yours don't it, you can synchronize manully from `File > Sync Project with Grale File`.
5. Please select `app` by build configuration.

<img width="600" alt="" src="https://user-images.githubusercontent.com/25496478/67455522-cd207980-f668-11e9-9613-5748d428266f.png">
6. You can build demo app with `Run`.

<img width="600" alt="" src="https://user-images.githubusercontent.com/25496478/67455523-ce51a680-f668-11e9-9997-7180aec39c14.png">


7. You can look application running logs on `Logcat` panel of debug area.

## Setup
1. Please set `SDK Token` of your repro application to `MainActivity.kt > setupRepro() -> setup('YOUR_TOKEN')`.
2. If you change Repro SDK Version, you can change sdk version of `app > build.gradle > dependencies`.

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
Spring theme is applied if `spring_mode` is set `true`.
If you enable Spring Mode, you cannot change `title_color`.

| Normal Mode |  Spring Mode  |
|---|---|
|<img width="170" alt="normal mode" src="https://user-images.githubusercontent.com/25496478/74325667-5f8d8a00-4dcc-11ea-93cc-e70e99ccffa7.png">|<img width="170" alt="spring mode" src="https://user-images.githubusercontent.com/25496478/74325677-61efe400-4dcc-11ea-96b1-3efcef70da04.png">|

| Button Position A | Button Position B |
|---|---|
|<img width="170" alt="button position A" src="https://user-images.githubusercontent.com/25496478/74325661-5d2b3000-4dcc-11ea-945a-b142105b467f.png">|<img width="170" alt="button position B" src="https://user-images.githubusercontent.com/25496478/74325663-5e5c5d00-4dcc-11ea-9e97-c4fc306d21e3.png">|





