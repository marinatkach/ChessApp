# Android Geo App 

## ТЗ


### Функции, программирование, ошибки

[ ] editable radius in admin
[ ] Map: пока не загрузишь карту, не высвечивается расстояние до мест
[x] Бонус если нет бонусов, то высветить текст, проверить работоспособность. Супербонус, 2 сайта как линк
[ ] Админ, если делаю логаут, то не могу снова ввести пароль
[ ] Админ, когда ввел пароль, можно ли сделать, чтобы исчезло окошко ввода текста
[ ] Админ добавить ввод радиуса

### Контексе

[ ] Админ вводный текст вставить вначале

[ ] add photo and text to history (17 persons), citate after photo
[ ] add game images to db + places + solution (can be long)
[ ] add place descriptions (info, name, adress, image)
[ ] each place contains 5 games + 1 bonus
[ ] send old project
[ ] Начальная страница, другое фото, чтобы был виден текст
[ ] Меню заменить икону андроида на свою
[ ] Заменить иконки стандартные на другие (слева)
[ ] Добавить ко всем шахматным местам (31 место) вводный текст и сайт, 5 задач,
[ ] В puzzles, показать все задачи (155 штук), Добавить вводный тест, название клуба, потом 5 задач, название клуба...
   

[ ] Map: заменить иконку outdoor(компас) на другую
[x] Map: заменить цвет иконок на более светлый
[ ]  History добавить всех чемпионов мира (17 штук) и описание, цитату сдвинуть после фото, добавить линию, разделющую ЧМ. В заголовке увеличить размер текста.


### Поддержка

[ ] не загружается в виртуалке
[ ] телефон подклюить к студио 
[ ] день на проверку багов.
[ ] исправить баги, если появятся
[ ] помочь исправить, если профессорша скажет изменить что-то
[ ] помочь загрузить всё в Ирин git
[ ] Если найдется баг - оперативно исправить
[ ] Перед сдачей, объяснить Ире структуру программы





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
