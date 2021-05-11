# library-springboot
`Backend web library react yang dibuat dengan menggunakan bahasa java dengan framework spring boot`

Backend ini terhubung pada repository library react js : https://github.com/ulimultia/library_react

# Cara installasi
- Pertama silahkan download sql yang akan digunakan pada backend ini :
[db_kreasitech.zip](https://github.com/alfianaf/library-springboot/files/6457541/db_kreasitech.zip)
- Import db yang sudah didownload pada dbms mysql masing2
- Anda dapat menjalankan aplikasi Spring Boot dari IDE Anda sebagai aplikasi Java sederhana, namun, Anda perlu mengimpor proyek Anda terlebih dahulu. Langkah-langkah pengimporan akan bervariasi bergantung pada IDE dan sistem build Anda. Kebanyakan IDE dapat mengimpor proyek Maven secara langsung, misalnya pengguna Eclipse dapat memilih Import…​ → Existing Maven Projects dari menu File.
- Buka file application.properties yang ada pada directory src/main/resources/application.properties
- Ubah spring.datasource.url sesuai dengan database yang diimport sebelumnya, (jika nama db "library-react" maka anda tidak perlu mengubah application.properties ini)


Setelah itu pada command line directory terkait, dapat menjalankan

```$ mvn spring-boot:run```

Bagi yang sudah menginstall Spring Boot Dashboard pada IDE nya bisa run lewat dashboard masing2

![image](https://user-images.githubusercontent.com/38254455/117782860-3af0be00-b26c-11eb-9502-bdbf8753fc5d.png)

*disarankan sudah menginstall maven sebelumnya terlebih dahulu*


