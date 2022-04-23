-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 29 nov. 2021 à 10:54
-- Version du serveur : 10.4.21-MariaDB
-- Version de PHP : 8.0.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `gestiongarderie`
--

-- --------------------------------------------------------

--
-- Structure de la table `adresse`
--

CREATE TABLE `adresse` (
  `id_adresse` varchar(50) COLLATE armscii8_bin NOT NULL,
  `numeroRue_adresse` varchar(50) COLLATE armscii8_bin NOT NULL,
  `libelle_adresse` varchar(50) COLLATE armscii8_bin NOT NULL,
  `ville_adresse` varchar(50) COLLATE armscii8_bin NOT NULL,
  `postal_adresse` varchar(50) COLLATE armscii8_bin NOT NULL,
  `gouvernorat_adresse` varchar(50) COLLATE armscii8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

--
-- Déchargement des données de la table `adresse`
--

INSERT INTO `adresse` (`id_adresse`, `numeroRue_adresse`, `libelle_adresse`, `ville_adresse`, `postal_adresse`, `gouvernorat_adresse`) VALUES
('1', '1', 'mm', 'pp', '1254', 'oo');

-- --------------------------------------------------------

--
-- Structure de la table `animateur`
--

CREATE TABLE `animateur` (
  `cin_animateur` varchar(50) COLLATE armscii8_bin NOT NULL,
  `nom_animateur` varchar(50) COLLATE armscii8_bin NOT NULL,
  `prenom_animateur` varchar(50) COLLATE armscii8_bin NOT NULL,
  `telephone_animateur` varchar(11) COLLATE armscii8_bin NOT NULL,
  `pere_animateur` varchar(50) COLLATE armscii8_bin NOT NULL,
  `date_naissance_animateur` date DEFAULT NULL,
  `adresse_animateur` varchar(50) COLLATE armscii8_bin NOT NULL,
  `image_animateur` varchar(50) COLLATE armscii8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=armscii8 COLLATE=armscii8_bin;

--
-- Déchargement des données de la table `animateur`
--

INSERT INTO `animateur` (`cin_animateur`, `nom_animateur`, `prenom_animateur`, `telephone_animateur`, `pere_animateur`, `date_naissance_animateur`, `adresse_animateur`, `image_animateur`) VALUES
('1', 'mm', 'pp', '1254', 'pp', '2000-12-12', '1', 'mm');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `adresse`
--
ALTER TABLE `adresse`
  ADD PRIMARY KEY (`id_adresse`);

--
-- Index pour la table `animateur`
--
ALTER TABLE `animateur`
  ADD PRIMARY KEY (`cin_animateur`),
  ADD KEY `adresse_animateur` (`adresse_animateur`);

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `animateur`
--
ALTER TABLE `animateur`
  ADD CONSTRAINT `animateur_ibfk_1` FOREIGN KEY (`adresse_animateur`) REFERENCES `adresse` (`id_adresse`) ON DELETE NO ACTION ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_adresse` FOREIGN KEY (`adresse_animateur`) REFERENCES `adresse` (`id_adresse`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
