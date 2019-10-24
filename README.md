# remote-config-demo

## How to build application.
1. Install Android Studio `3.4` ~.
2. Pull this repository.
3. Open this project root with Android Studio.
4. Android Studio will synchronize automatically gradle. If yours don't it, you can synchronize manully from `File > Sync Project with Grale File`.
5. Please select `app` by build configuration.

<img width="300" alt="スクリーンショット 2019-10-24 14 11 56" src="https://user-images.githubusercontent.com/25496478/67455522-cd207980-f668-11e9-9613-5748d428266f.png">
6. You can build demo app with `Run`.

<img width="300" alt="スクリーンショット 2019-10-24 14 11 56 2" src="https://user-images.githubusercontent.com/25496478/67455523-ce51a680-f668-11e9-9997-7180aec39c14.png">

## UX-Optimizer Parameters

|Key|  Type |  Example  |
|---|---|---|
|title|  string  |  Tシャツ  |
|message|  string  | 夏に着るおしゃれアイテム！ |
|title_color|  string  | 333333 |
|spring_mode|  string  | true |

## Change Theme
Spring theme is applied if `spring_mode` is set `true`.
<img width="300" alt="スクリーンショット 2019-10-24 14 11 56 2" src="https://user-images.githubusercontent.com/25496478/67455467-a2cebc00-f668-11e9-91ef-b34df1ca2bdc.png">
![season_ux_optimizer](https://user-images.githubusercontent.com/25496478/67455467-a2cebc00-f668-11e9-91ef-b34df1ca2bdc.png)
