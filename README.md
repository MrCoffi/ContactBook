# ContactBook
Настройки properties: 
Сейчас стоит spring.profiles.active=init, так же может стоять defualt.

1)Вызов метода получения всех записей из тестового файла : info(В случае если стоит defualt,список записей будет пуст).
2)Для добавления необходимо написать 3 параметра через (точку с запятой) 
  Пример :
  input: 1;1;1@mail.ru
3)Для удаления записи необходимо написать remove.
После получения сообщения "Please write to remove e-mail" необходимо написать mail.
  Пример :
  input:remove
  out: Please write to remove e-mail
  input:1@mail.ru

Для Сохранения данных в любом из профилей необходимо написать в концоль "0"
При этом произайдет завершение програмы.
  Пример : 
    input:0

При сохранении дублей дубли будут уничтожены при следующем запуске программы из профиля init. Это достигается использованием листа Set.