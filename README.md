# Aplikasi Sederhana API XSIS Movie

Aplikasi diperuntukan untuk memenuhi salah satu syarat untuk mengikuti tahap 1 interview dengan PT Xsis Mitra Usaha. Diantara fiitur yang terdapat pada aplikasi ini adalah sebagai berikut:
* Menampilkan semua data
* Menampilakn detail data berdasarkan id
* Menambahkan data baru
* Menghapus data berdasarkan id
* Melakukan perubahan data berdasarkan id 

# Cara Setup DataBase
1. Jalankan PostgreSQL di Docker
    ```
    docker run --rm \
      --name xsis-movie-db \
      -e POSTGRES_DB=moviedb \
      -e POSTGRES_USER=xsis \
      -e POSTGRES_PASSWORD=RoWkaHOBhPlJan46NJ1d \
      -e PGDATA=/var/lib/postgresql/data/pgdata \
      -v "$PWD/xsis-moviedb-data:/var/lib/postgresql/data" \
      -p 5432:5432 \
      postgres:15
   ```
