# Notification

<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/notification1.png" width="200" height="400"/>
<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/notification2.png" width="200" height="400"/>
<img src="https://github.com/HYUNJUNEPARK/ImageRepository/blob/master/androidProgramming/notification3.png" width="200" height="400"/>

-API 26 부터 채널 개념이 추가됨</br>
-앱의 알림을 채널로 구분할 수 있으며 하나의 앱에서 원하는 채널의 알림만 받을 수 있게됨</br>
ex)광고 알림은 받지 않지만 채팅방 생성 알림은 받게 설정 가능</br>

<br></br>
**1 알림 채널**</br>

`NotificationChannel(id:String!, name: CharSequence!, importance: Int)`</br>

importance</br>
`NotificationManager.IMPORTANCE_HIGH` : 긴급 상황으로 알림이 울리며 헤드업으로 표시</br>
`NotificationManager.IMPORTANCE_DEFAULT` : 높은 중요도이며 알림음이 울림</br>
`NotificationManager.IMPORTANCE_LOW` : 중간 중요도이며 알림음이 울리지 않음</br>
`NotificationManager.IMPORTANCE_MIN` : 낮은 중요도 이며 알림음이 없고 상태 바에도 표시되지 않음</br>

채널 설정 함수/프로퍼티</br>
`fun setDescription(description : String!) : Unit` : 파라미터로 전달한 문자열은 설정 화면에서 채널을 설명하는 곳에 보임</br>
`fun setShowBadge(showBadge: Boolean) : Unit` : 홈 화면 앱 아이콘에 확인하지 않은 알림 개수가 표시된 배지 아이콘이 표시</br>
`fun setSound(sound: Uri!, audioAttributes: AudioAttributes!) : Unit` : 알림음 재생</br>
`fun enableLights(lights: Boolean) : Unit` : 불빛 표시 여부</br>
`fun setLightColor(argb: Int) : Unit` : 불빛이 표시된다면 불빛 색상</br>
`fun enableVibration(vibration: Boolean) : Unit` : 진동을 울릴지 여부</br>
`fun setVibrationPatter(vibrationPatter: LongArray!) : Unit` : 진동이 울린다면 진동 패턴</br>

<br></br>
**2 알림 객체**</br>

RemoteInput 은 API 20 에서 추가됨</br>
이전 버전에서는 호환성 생각할 것(android.app.RemoteInput -> androidx.core.app.RemoteInput 사용)</br>










