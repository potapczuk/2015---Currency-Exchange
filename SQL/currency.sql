-- --------------------------------------------------------
-- Servidor:                     127.0.0.1
-- Versão do servidor:           5.1.33-community - MySQL Community Server (GPL)
-- OS do Servidor:               Win32
-- HeidiSQL Versão:              8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Copiando estrutura do banco de dados para currency-exchange
CREATE DATABASE IF NOT EXISTS `currency-exchange` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `currency-exchange`;


-- Copiando estrutura para tabela currency-exchange.currency
CREATE TABLE IF NOT EXISTS `currency` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `shortName` varchar(255) DEFAULT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela currency-exchange.currency: ~3 rows (aproximadamente)
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
INSERT INTO `currency` (`id`, `version`, `name`, `shortName`, `symbol`) VALUES
	(1, 0, 'US Dollar', 'USD', '$'),
	(2, 0, 'Euro', 'EUR', '€'),
	(3, 0, 'Brazilian Real', 'BRL', 'R$');
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;


-- Copiando estrutura para tabela currency-exchange.currency_exchange
CREATE TABLE IF NOT EXISTS `currency_exchange` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `inverseValue` decimal(20,8) DEFAULT NULL,
  `value` decimal(20,8) DEFAULT NULL,
  `leftCurrency_id` bigint(20) DEFAULT NULL,
  `rightCurrency_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ncx0akwknk4m617u8w071sf3m` (`leftCurrency_id`),
  KEY `FK_3apdpu4tao06ww7ayug56xrfy` (`rightCurrency_id`),
  CONSTRAINT `FK_3apdpu4tao06ww7ayug56xrfy` FOREIGN KEY (`rightCurrency_id`) REFERENCES `currency` (`id`),
  CONSTRAINT `FK_ncx0akwknk4m617u8w071sf3m` FOREIGN KEY (`leftCurrency_id`) REFERENCES `currency` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela currency-exchange.currency_exchange: ~6 rows (aproximadamente)
/*!40000 ALTER TABLE `currency_exchange` DISABLE KEYS */;
INSERT INTO `currency_exchange` (`id`, `version`, `inverseValue`, `value`, `leftCurrency_id`, `rightCurrency_id`) VALUES
	(1, 0, 1.13054000, 0.88453100, 1, 2),
	(2, 0, 0.88453100, 1.13054000, 2, 1),
	(3, 0, 0.25344900, 3.94557000, 1, 3),
	(4, 0, 3.94557000, 0.25344900, 3, 1),
	(5, 0, 0.22419400, 4.46042000, 2, 3),
	(6, 0, 4.46042000, 0.22419400, 3, 2);
/*!40000 ALTER TABLE `currency_exchange` ENABLE KEYS */;


-- Copiando estrutura para tabela currency-exchange.user
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `version` bigint(20) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `passwordDigest` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- Copiando dados para a tabela currency-exchange.user: ~1 rows (aproximadamente)
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`id`, `version`, `email`, `passwordDigest`, `username`) VALUES
	(1, 0, 'test@email.com', '$2a$10$x9vXeDsSC2109FZfIJz.pOZ4dJ056xBpbesuMJg3jZ.ThQkV119tS', 'test123');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
