

-- phpMyAdmin SQL Dump
-- version 4.7.7
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 24-07-2018 a las 15:26:58
-- Versión del servidor: 10.1.31-MariaDB
-- Versión de PHP: 7.0.26

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `id6534422_demo_restful`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` varchar(10) COLLATE utf8_unicode_ci NOT NULL,
  `hash_password` varchar(256) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `address` varchar(128) COLLATE utf8_unicode_ci NOT NULL,
  `gender` enum('F','M') COLLATE utf8_unicode_ci NOT NULL,
  `token` varchar(255) COLLATE utf8_unicode_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `hash_password`, `name`, `address`, `gender`, `token`) VALUES
('carlos', '$2y$10$eI14pf0wR2O.IdhLctbmHew0bB4pHG51CPwQlj5zlnl9dhASvNR8C', 'Carlos', 'Calle 16, Irpavi.', 'M', '14888819115b571094279a63.53985430'),
('gus', '$2y$10$SPRhoTQRZo0ttYV2F//IjeKC71VTqVPr4gZqtkwOAs2YmlcQYxQf2', 'Gustavo', 'Calle 20, Obrajes.', 'M', '13786827245b4fa874d08703.45516040'),
('javier', '$2y$10$MFsrcYt5jAGoB6byZD/diOHpPqyeSAFGDkwWOE0frz7GjvHBa0CvG', 'Javier Yucra', 'Ciudad Satelite', 'M', '8310063425b5710b9adc418.61069295'),
('javyz', '$2y$10$WA/t9Sgx35aLmtCrrAJeyOuKOzGTH8H6Fz2QipMDhasuY5gZZwPqG', 'Javier Yucra', 'Ciudad Satelite', 'M', '1621862525b571d5b25df22.67009533'),
('jorge', '$2y$10$AoJHBrpQNifBlOqomYJ1p.Q5zRQ5mjbxjnQH48ky6yiUhO24/gR2y', 'Jorge Medina', 'San Pedro, calle gral. Gonzales', 'M', '3873170355b5710b4c30046.09715677'),
('josue', '$2y$10$GBSqaM3HZCgPypebY1Xvi.pCfSIwyzOBo.KUCzh2Qk7CmAK4rVhly', 'Josue Lizarraga', 'Obrajes, calle 19', 'M', '932609545b5710b492ba81.11269333'),
('maria', '$2y$10$L7MyaNbUEH6/3aNsJPp5ReNVfZ..lOwMinUsHJrJrM8lKP4RuV.7i', 'Maria', 'Plaza España, Sopocachi.', 'F', '21059129245b4fc2641aded2.25112902'),
('milenka', '$2y$10$7XUdAmj.3tzz9FJ5D9.sY.05KoH7LMDjy0ncA4RoX18SrvxFQK9b2', 'Milenka Oporto', 'San Pedro', 'F', '8196766045b5711be206bf5.63818600'),
('pepito', '$2y$10$qNbLP9MK68GdgUQ3wZWhBup/23UgC4eCbnKT9cq7tW6sGEuQlIwv2', 'Israel', 'Villa Salome.', 'M', '7028364415b571cd9d188b1.40894880'),
('sandra', '$2y$10$W3YiE5JJUeHFA//ZYqXOouPNJK39jks35xeMoO.dMfgWpxvaA6YQW', 'Sandra Omonte', 'Calle 15 de Obrajes', 'F', '12415493265b5711b81164e6.25582888');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
