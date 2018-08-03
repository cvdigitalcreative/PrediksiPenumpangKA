-- phpMyAdmin SQL Dump
-- version 4.7.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 30, 2018 at 09:34 AM
-- Server version: 10.1.28-MariaDB
-- PHP Version: 7.1.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `prediksi_penumpangka`
--

-- --------------------------------------------------------

--
-- Table structure for table `bobotbplm_v`
--

CREATE TABLE `bobotbplm_v` (
  `id_bobotvbplm` int(11) NOT NULL,
  `id_percobaan` int(11) NOT NULL,
  `node_inputbplm` int(11) NOT NULL,
  `node_hiddenbplm` int(11) NOT NULL,
  `nilai_bobotvbplm` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bobotbplm_v`
--

INSERT INTO `bobotbplm_v` (`id_bobotvbplm`, `id_percobaan`, `node_inputbplm`, `node_hiddenbplm`, `nilai_bobotvbplm`) VALUES
(1, 1, 0, 0, 488952.4103938531),
(2, 1, 0, 1, 681437.9077065256),
(3, 1, 0, 2, 619526.5453386753),
(4, 1, 1, 0, 667413.6619103119),
(5, 1, 1, 1, 393893.232213783),
(6, 1, 1, 2, 475393.44376840116),
(7, 1, 2, 0, 747102.8363816236),
(8, 1, 2, 1, 509180.83162219793),
(9, 1, 2, 2, 380940.9197867575),
(10, 1, 3, 0, 1048085.0662557995),
(11, 1, 3, 1, 899435.1063414041),
(12, 1, 3, 2, 379935.22081431444),
(13, 1, 4, 0, 438602.51188604836),
(14, 1, 4, 1, 1047096.5901383596),
(15, 1, 4, 2, 590577.1083031134),
(16, 1, 5, 0, 901473.6067808651),
(17, 1, 5, 1, 492225.7703021052),
(18, 1, 5, 2, 575671.480365199),
(19, 1, 6, 0, -813258512.1828251),
(20, 1, 6, 1, -11563014.477656784),
(21, 1, 6, 2, 787471.5870324003),
(22, 1, 7, 0, 375762.83288992144),
(23, 1, 7, 1, -616086.7674946624),
(24, 1, 7, 2, 840633.8389172923),
(25, 1, 8, 0, 1039697.484602392),
(26, 1, 8, 1, 454754.88375793974),
(27, 1, 8, 2, 360112.2487324852),
(28, 1, 9, 0, 824335.812114613),
(29, 1, 9, 1, 678590.6687798187),
(30, 1, 9, 2, 1043827.6383361841),
(31, 1, 10, 0, 389653.2517998437),
(32, 1, 10, 1, 361704.26404847717),
(33, 1, 10, 2, 831336.9214740007),
(34, 1, 11, 0, 1000473.7785954581),
(35, 1, 11, 1, 646580.3500690552),
(36, 1, 11, 2, 524923.3352328904),
(37, 1, 12, 0, 1022033.5476168094),
(38, 1, 12, 1, -279249.20821738173),
(39, 1, 12, 2, 391767.95664931);

-- --------------------------------------------------------

--
-- Table structure for table `bobotbplm_w`
--

CREATE TABLE `bobotbplm_w` (
  `id_bobotwbplm` int(11) NOT NULL,
  `id_percobaan` int(11) NOT NULL,
  `node_hiddenbplm` int(11) NOT NULL,
  `node_outputbplm` int(11) NOT NULL,
  `nilai_bobotwbplm` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bobotbplm_w`
--

INSERT INTO `bobotbplm_w` (`id_bobotwbplm`, `id_percobaan`, `node_hiddenbplm`, `node_outputbplm`, `nilai_bobotwbplm`) VALUES
(1, 1, 0, 0, 815588.1221082059),
(2, 1, 1, 0, 815588.6047077193),
(3, 1, 2, 0, 815588.120438284),
(4, 1, 3, 0, 815588.0391304282);

-- --------------------------------------------------------

--
-- Table structure for table `bobotbp_v`
--

CREATE TABLE `bobotbp_v` (
  `id_bobotv` int(11) NOT NULL,
  `id_percobaan` int(11) NOT NULL,
  `node_input` int(11) NOT NULL,
  `node_hidden` int(11) NOT NULL,
  `nilai_bobotv` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bobotbp_v`
--

INSERT INTO `bobotbp_v` (`id_bobotv`, `id_percobaan`, `node_input`, `node_hidden`, `nilai_bobotv`) VALUES
(1, 1, 0, 0, 0.6889508885773689),
(2, 1, 0, 1, 0.019233464706203938),
(3, 1, 0, 2, 0.7793002348803525),
(4, 1, 1, 0, 0.5738007746147776),
(5, 1, 1, 1, 0.28977905927730296),
(6, 1, 1, 2, 0.25679651316320184),
(7, 1, 2, 0, 0.1606595993492422),
(8, 1, 2, 1, 0.7149340906079058),
(9, 1, 2, 2, 0.037427044000051285),
(10, 1, 3, 0, 0.8826495881349947),
(11, 1, 3, 1, 0.8646731146185931),
(12, 1, 3, 2, 0.03207758066001419),
(13, 1, 4, 0, 0.6638181028735048),
(14, 1, 4, 1, 0.3190244881768265),
(15, 1, 4, 2, 0.09590460578121345),
(16, 1, 5, 0, 0.7611620340113144),
(17, 1, 5, 1, 0.4916332409743923),
(18, 1, 5, 2, 0.9805533715671303),
(19, 1, 6, 0, 0.120944372232244),
(20, 1, 6, 1, 0.3953313208305207),
(21, 1, 6, 2, 0.18171254790830355),
(22, 1, 7, 0, 0.43621443690367007),
(23, 1, 7, 1, 0.9761924894647592),
(24, 1, 7, 2, 0.10177924823119533),
(25, 1, 8, 0, 0.4172237662285129),
(26, 1, 8, 1, 0.6905494115360368),
(27, 1, 8, 2, 0.8830636782738276),
(28, 1, 9, 0, 0.21920075548665102),
(29, 1, 9, 1, 0.7953933691515095),
(30, 1, 9, 2, 0.5137593868187132),
(31, 1, 10, 0, 0.97107708807574),
(32, 1, 10, 1, 0.8236654961196386),
(33, 1, 10, 2, 0.6358203165890292),
(34, 1, 11, 0, 0.9983215432365586),
(35, 1, 11, 1, 0.7721468102088466),
(36, 1, 11, 2, 0.7702561162787763),
(37, 1, 12, 0, 0.34579533601462986),
(38, 1, 12, 1, 0.11057531789831195),
(39, 1, 12, 2, 0.5125763192941307);

-- --------------------------------------------------------

--
-- Table structure for table `bobotbp_w`
--

CREATE TABLE `bobotbp_w` (
  `id_bobotw` int(11) NOT NULL,
  `id_percobaan` int(11) NOT NULL,
  `node_hidden` int(11) NOT NULL,
  `node_output` int(11) NOT NULL,
  `nilai_bobotw` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `bobotbp_w`
--

INSERT INTO `bobotbp_w` (`id_bobotw`, `id_percobaan`, `node_hidden`, `node_output`, `nilai_bobotw`) VALUES
(1, 1, 0, 0, 0.9180212386912042),
(2, 1, 1, 0, 0.8195559731362292),
(3, 1, 2, 0, 1.1349688326727145),
(4, 1, 3, 0, 0.6938305071408719);

-- --------------------------------------------------------

--
-- Table structure for table `parameter_bp`
--

CREATE TABLE `parameter_bp` (
  `id_percobaan` int(11) NOT NULL,
  `epoch` int(11) NOT NULL,
  `learning_rate` double NOT NULL,
  `minimum_error` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parameter_bp`
--

INSERT INTO `parameter_bp` (`id_percobaan`, `epoch`, `learning_rate`, `minimum_error`) VALUES
(1, 1000, 0.3, 0.0005);

-- --------------------------------------------------------

--
-- Table structure for table `parameter_bplm`
--

CREATE TABLE `parameter_bplm` (
  `id_percobaan_bplm` int(11) NOT NULL,
  `epoch` int(11) NOT NULL,
  `lm_parameter` double NOT NULL,
  `beta_parameter` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `parameter_bplm`
--

INSERT INTO `parameter_bplm` (`id_percobaan_bplm`, `epoch`, `lm_parameter`, `beta_parameter`) VALUES
(1, 1000, 0.1, 0.02);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bobotbplm_v`
--
ALTER TABLE `bobotbplm_v`
  ADD PRIMARY KEY (`id_bobotvbplm`);

--
-- Indexes for table `bobotbplm_w`
--
ALTER TABLE `bobotbplm_w`
  ADD PRIMARY KEY (`id_bobotwbplm`);

--
-- Indexes for table `bobotbp_v`
--
ALTER TABLE `bobotbp_v`
  ADD PRIMARY KEY (`id_bobotv`);

--
-- Indexes for table `bobotbp_w`
--
ALTER TABLE `bobotbp_w`
  ADD PRIMARY KEY (`id_bobotw`);

--
-- Indexes for table `parameter_bp`
--
ALTER TABLE `parameter_bp`
  ADD PRIMARY KEY (`id_percobaan`);

--
-- Indexes for table `parameter_bplm`
--
ALTER TABLE `parameter_bplm`
  ADD PRIMARY KEY (`id_percobaan_bplm`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
