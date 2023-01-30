
--Franchises
INSERT INTO franchises(name,description) VALUES
('Marvel Universe','A superheroes movie franchise made in United states of America'),
('The Lord of the Rings','An epic high-fantasy novel by English author and scholar J. R. R. Tolkien.'),
('Pixar-Disney Animations','Pixar has produced 26 feature films');

--Movies
INSERT INTO movies (title,genre,release_year,director,picture,trailer) VALUES
('Spider Man','Adventure, Action','2002','Sam Raimi','https://upload.wikimedia.org/wikipedia/en/f/f3/Spider-Man2002Poster.jpg','https://www.youtube.com/watch?v=t06RUxPbp_c&ab_channel=SonyPicturesEntertainment'),
('The lord of the rings','Action, Adventure, Fantasy','2001','Peter Jackson','https://i-viaplay-com.akamaized.net/viaplay-prod/606/408/1460127006-8faa6035eafd4487259fb06def95945ed739ede0.jpg?width=400&height=600','https://www.youtube.com/watch?v=uYnQDsaxHZU&ab_channel=PrimeVideo'),
('Toy story','Animation, Action','1995','John Lasseter','https://m.media-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_.jpg','https://m.media-amazon.com/images/M/MV5BMDU2ZWJlMjktMTRhMy00ZTA5LWEzNDgtYmNmZTEwZTViZWJkXkEyXkFqcGdeQXVyNDQ2OTk4MzI@._V1_.jpg');

--Characters
INSERT INTO characters (full_name,alias,gender,picture) VALUES
('Tobey Maguire','Peter Parker','Male','https://www.themoviedb.org/t/p/w500/kOJelnLSb89SeivbOCt1l94Hz2d.jpg'),
('Elijah Wood','Frodo Baggins','Male','https://m.media-amazon.com/images/M/MV5BMTM0NDIxMzQ5OF5BMl5BanBnXkFtZTcwNzAyNTA4Nw@@._V1_.jpg'),
('Tom Hanks','Woody','Male','https://m.media-amazon.com/images/M/MV5BMTQ2MjMwNDA3Nl5BMl5BanBnXkFtZTcwMTA2NDY3NQ@@._V1_.jpg');

--Movies in characters
INSERT INTO movies_characters(movie_id, character_id) VALUES
(10,10),
(11,11),
(12,12);

