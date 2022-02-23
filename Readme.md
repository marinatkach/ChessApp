# Android Geo App 

## Как добавить иконки в меню
1. открыть res/menu/activity_main_drawer.xml
2. изменить id на необходимую иконку
   ```android:icon="@drawable/ic_menu_camera"```
   
## Как изменить иконки на карте 
1. загрузить в drawable 9 иконок
   
   * ic_outdoor_black.xml
   * ic_outdoor_near.xml
   * ic_outdoor_selected.xml
     
   * ic_club_black.xml
   * ic_club_near.xml
   * ic_club_selected.xml
     
   * ic_cafe_black.xml
   * ic_cafe_near.xml
   * ic_cafe_selected.xml
    
    Где:
   * ic_cafe_* - значки кафе ,
   * ic_club_* - значки клуба, 
   * ic_outdoor_* - значки мест на улице
     
    А так же :
   * *_black - обычные значки (цвет не должен быть обязательно черным) ,
   * *_near  - значек когда место находится близко,
   * *_selected - когда место было посещенно 
    

## Как изменить текст 
## Как изменить ключевые параметры

    public static final int DISTANCE_METERS = 1000;
    public static final int DISTANCE_TO_ADD_TO_VISITED_METES = 100;

    public static final int DISTANCE_TO_PLACE_NEAR_METERS = 200;
    public static final int DISTANCE_TO_PLACE_MIDDLE_METERS = 700;
    public static final int DISTANCE_SET_AS_VISITED_BY_RADIUS_METERS = 10000;

## Как обновить базу данных 
## Как внести данные в базу данных 
