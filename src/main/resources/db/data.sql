truncate table users cascade;
truncate table media cascade;

insert into users(id, email,password,time_created) values
(200,'email@gmail.com','password','2024-06-04T15:03:03.7992009700'),
(201,'email12@gmail.com','password','2024-06-04T15:03:03.7992009700'),
(202,'email1@gmail.com','password','2024-06-04T15:03:03.7992009700');

insert into media (id, category,description,url,time_created,uploader_id) values
(100, 'ACTION', 'media 1', 'https://cloudinary.com/media1','2024-06-04T15:03:03.7992009700',200),
(102, 'SCI_FI', 'media 3', 'https://cloudinary.com/media3','2024-06-04T15:03:03.7992009700',200),
(105, 'HORROR', 'media 2', 'https://cloudinary.com/media3','2024-06-04T15:03:03.7992009700',201),
(101, 'ROMANCE', 'media 2', 'https://cloudinary.com/media2','2024-06-04T15:03:03.7992009700',202),
(103, 'COMEDY', 'media 4', 'https://cloudinary.com/media4','2024-06-04T15:03:03.7992009700',201);

