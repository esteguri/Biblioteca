-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 09-08-2019 a las 20:59:51
-- Versión del servidor: 10.3.16-MariaDB
-- Versión de PHP: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `store`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `compra`
--

CREATE TABLE `compra` (
  `idCompra` int(11) NOT NULL,
  `idUsuario` int(11) NOT NULL,
  `fecha` varchar(30) NOT NULL,
  `medioPago` varchar(30) NOT NULL,
  `productos` mediumtext NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `compra`
--

INSERT INTO `compra` (`idCompra`, `idUsuario`, `fecha`, `medioPago`, `productos`) VALUES
(1, 1, '29-07-2019', 'PSE', '[{\"idProducto\":3,\"valorProducto\":30.0,\"nombreProducto\":\"Nintendo\",\"cantidad\":1},{\"idProducto\":6,\"valorProducto\":22.6,\"nombreProducto\":\"Xbox 360\",\"cantidad\":1}]'),
(2, 2, '30-07-2019', 'PayPal', '[{\"idProducto\":3,\"valorProducto\":30.0,\"nombreProducto\":\"Nintendo\",\"cantidad\":1},{\"idProducto\":2,\"valorProducto\":30.0,\"nombreProducto\":\"Play Station 4\",\"cantidad\":1}]'),
(3, 1, '30-06-2019', 'Tarjeta de Credito', '[{\"idProducto\":4,\"valorProducto\":12.0,\"nombreProducto\":\"PSP Vita\",\"cantidad\":1}]'),
(4, 1, '31-07-2019', 'PSE', '[{\"idProducto\":2,\"valorProducto\":30.0,\"nombreProducto\":\"Play Station 4\",\"cantidad\":1}]'),
(5, 2, '31-07-2019', 'PayPal', '[{\"idProducto\":1,\"valorProducto\":25.0,\"nombreProducto\":\"Xbox One S\",\"cantidad\":1}]'),
(6, 1, '09-08-2019', 'Tarjeta de Credito', '[{\"idProducto\":3,\"valorProducto\":30.0,\"nombreProducto\":\"Nintendo\",\"cantidad\":2}]'),
(7, 1, '09-08-2019', 'Tarjeta de Credito', '[{\"idProducto\":3,\"valorProducto\":30.0,\"nombreProducto\":\"Nintendo\",\"cantidad\":1}]');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--

CREATE TABLE `producto` (
  `idProducto` int(11) NOT NULL,
  `nombreProdcuto` varchar(40) DEFAULT NULL,
  `valorProducto` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `producto`
--

INSERT INTO `producto` (`idProducto`, `nombreProdcuto`, `valorProducto`) VALUES
(1, 'Xbox One S', 25),
(2, 'Play Station 4', 30),
(3, 'Nintendo', 30),
(4, 'PSP Vita', 12),
(5, 'Play Station 1', 12.6),
(6, 'Xbox 360', 22.6);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `usuario` varchar(10) NOT NULL,
  `password` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`idUsuario`, `usuario`, `password`) VALUES
(1, 'egutierrez', '12345'),
(2, 'esteguri', '321');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `compra`
--
ALTER TABLE `compra`
  ADD PRIMARY KEY (`idCompra`),
  ADD KEY `fk_idUsuario` (`idUsuario`);

--
-- Indices de la tabla `producto`
--
ALTER TABLE `producto`
  ADD PRIMARY KEY (`idProducto`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`idUsuario`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `compra`
--
ALTER TABLE `compra`
  MODIFY `idCompra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT de la tabla `producto`
--
ALTER TABLE `producto`
  MODIFY `idProducto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `idUsuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `compra`
--
ALTER TABLE `compra`
  ADD CONSTRAINT `fk_idUsuario` FOREIGN KEY (`idUsuario`) REFERENCES `usuario` (`idUsuario`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
