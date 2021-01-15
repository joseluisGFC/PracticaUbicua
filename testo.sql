-- phpMyAdmin SQL Dump
-- version 5.0.3
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 15-01-2021 a las 18:27:18
-- Versión del servidor: 10.4.14-MariaDB
-- Versión de PHP: 7.2.34

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `testo`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alerta`
--

CREATE TABLE `alerta` (
  `id` int(4) NOT NULL,
  `nombre` char(20) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ciudad`
--

CREATE TABLE `ciudad` (
  `id_ciudad` int(11) NOT NULL,
  `nombre` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ciudad`
--

INSERT INTO `ciudad` (`id_ciudad`, `nombre`) VALUES
(1, 'Alcala'),
(2, 'Guadalajara'),
(2, 'Madrid'),
(2, 'Torrejon');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contenedor`
--

CREATE TABLE `contenedor` (
  `id` varchar(8) COLLATE latin1_spanish_ci NOT NULL,
  `CIF` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `latitud` double NOT NULL,
  `longuitud` double NOT NULL,
  `id_ciudad` int(11) NOT NULL,
  `nombre` varchar(20) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `contenedor`
--

INSERT INTO `contenedor` (`id`, `CIF`, `latitud`, `longuitud`, `id_ciudad`, `nombre`) VALUES
('1', '123456a', 40.5053455, -3.3481092, 1, ''),
('2', '123456a', 40.474351445564444, -3.3833425421425574, 1, ''),
('3', '123456a', 40.475433991593476, -3.3788401832809, 1, '');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `contrato`
--

CREATE TABLE `contrato` (
  `CIF` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` varchar(15) COLLATE latin1_spanish_ci NOT NULL,
  `direccion` varchar(20) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `contrato`
--

INSERT INTO `contrato` (`CIF`, `nombre`, `direccion`) VALUES
('123456a', 'estean', 'dafdfas');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medida`
--

CREATE TABLE `medida` (
  `fecha` date NOT NULL,
  `valor` int(4) NOT NULL,
  `id_contenedor` varchar(8) COLLATE latin1_spanish_ci NOT NULL,
  `id_sensor` varchar(4) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `medida`
--

INSERT INTO `medida` (`fecha`, `valor`, `id_contenedor`, `id_sensor`) VALUES
('2021-01-14', 20, '1', '1'),
('2021-01-14', 40, '1', '2'),
('2021-01-14', 30, '2', '3'),
('2021-01-14', 1, '1', '4'),
('2021-01-14', 90, '2', '5');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sensor`
--

CREATE TABLE `sensor` (
  `id` varchar(8) COLLATE latin1_spanish_ci NOT NULL,
  `nombre` char(20) COLLATE latin1_spanish_ci NOT NULL,
  `valor_min` int(11) NOT NULL,
  `valor_max` int(11) NOT NULL,
  `valor_alerta` int(11) NOT NULL,
  `tipoSensor` varchar(16) COLLATE latin1_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `sensor`
--

INSERT INTO `sensor` (`id`, `nombre`, `valor_min`, `valor_max`, `valor_alerta`, `tipoSensor`) VALUES
('1', 'Sensor1', 1, 100, 80, 'Inclinacion'),
('2', 'Sensor2', 1, 100, 50, 'Temperatura'),
('3', 'Sensor3', 1, 100, 80, 'Inclinacion'),
('4', 'Sensor4', 1, 100, 80, 'Llenado'),
('5', 'Sensor5', 1, 100, 80, 'Llenado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipoalerta`
--

CREATE TABLE `tipoalerta` (
  `id_sensor` varchar(4) COLLATE latin1_spanish_ci NOT NULL,
  `id_alerta` int(4) NOT NULL,
  `nombre` char(20) COLLATE latin1_spanish_ci NOT NULL,
  `activo` int(1) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alerta`
--
ALTER TABLE `alerta`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `contenedor`
--
ALTER TABLE `contenedor`
  ADD PRIMARY KEY (`id`),
  ADD KEY `Relationship5` (`CIF`);

--
-- Indices de la tabla `contrato`
--
ALTER TABLE `contrato`
  ADD PRIMARY KEY (`CIF`);

--
-- Indices de la tabla `medida`
--
ALTER TABLE `medida`
  ADD PRIMARY KEY (`id_sensor`,`id_contenedor`),
  ADD KEY `Relationship0` (`id_contenedor`);

--
-- Indices de la tabla `sensor`
--
ALTER TABLE `sensor`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipoalerta`
--
ALTER TABLE `tipoalerta`
  ADD PRIMARY KEY (`id_sensor`,`id_alerta`),
  ADD KEY `Relationship3` (`id_alerta`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `contenedor`
--
ALTER TABLE `contenedor`
  ADD CONSTRAINT `Relationship5` FOREIGN KEY (`CIF`) REFERENCES `contrato` (`CIF`);

--
-- Filtros para la tabla `medida`
--
ALTER TABLE `medida`
  ADD CONSTRAINT `Relationship0` FOREIGN KEY (`id_contenedor`) REFERENCES `contenedor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `Relationship2` FOREIGN KEY (`id_sensor`) REFERENCES `sensor` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Filtros para la tabla `tipoalerta`
--
ALTER TABLE `tipoalerta`
  ADD CONSTRAINT `Relationship` FOREIGN KEY (`id_sensor`) REFERENCES `sensor` (`id`),
  ADD CONSTRAINT `Relationship3` FOREIGN KEY (`id_alerta`) REFERENCES `alerta` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
