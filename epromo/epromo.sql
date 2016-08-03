-- phpMyAdmin SQL Dump
-- version 4.2.7.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 03, 2016 at 08:29 PM
-- Server version: 5.6.20
-- PHP Version: 5.5.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `epromo`
--

-- --------------------------------------------------------

--
-- Table structure for table `ep_masterpromo`
--

CREATE TABLE IF NOT EXISTS `ep_masterpromo` (
`nomor` int(11) NOT NULL,
  `nama` varchar(55) DEFAULT NULL,
  `kategori` varchar(55) DEFAULT NULL,
  `alamat` varchar(55) DEFAULT NULL,
  `kontak` varchar(55) DEFAULT NULL,
  `deskripsi` varchar(55) DEFAULT NULL,
  `gambar` varchar(55) DEFAULT NULL
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=60 ;

--
-- Dumping data for table `ep_masterpromo`
--

INSERT INTO `ep_masterpromo` (`nomor`, `nama`, `kategori`, `alamat`, `kontak`, `deskripsi`, `gambar`) VALUES
(56, 'Guege', 'Food & Beverages', 'Ngagel', '085743560099', 'Coba deh', 'localhost:81/epromo/image/image/58.png'),
(57, 'Maneh wes', 'Food & Beverages', 'Ngagel', '085743500917', 'Second try', 'localhost:81/epromo/image/image/58.png'),
(58, 'Tri', 'Food & Beverages', 'Ngagel', '0857435600000', 'Thrie', 'localhost:81/epromo/image/image/58.png'),
(59, 'baju', 'Clothes', 'surabaya', '62782', 'baju bagus', 'localhost:81/epromo/image/image/59.png');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `ep_masterpromo`
--
ALTER TABLE `ep_masterpromo`
 ADD UNIQUE KEY `nomor` (`nomor`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `ep_masterpromo`
--
ALTER TABLE `ep_masterpromo`
MODIFY `nomor` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=60;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
