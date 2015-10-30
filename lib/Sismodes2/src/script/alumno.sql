SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';


-- -----------------------------------------------------
-- Table `sismodes_1`.`nota`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `sismodes_1`.`nota` (
  `id_nota` INT NOT NULL AUTO_INCREMENT,
  `cedula` VARCHAR(10) NULL,
  `nota1` DOUBLE NULL DEFAULT '0',
  `nota2` DOUBLE NULL DEFAULT '0',
  `nota3` DOUBLE NULL DEFAULT '0',
  `nota4` DOUBLE NULL DEFAULT '0',
  `nota5` DOUBLE NULL DEFAULT '0',
  `nota6` DOUBLE NULL DEFAULT '0',
  `nota7` DOUBLE NULL DEFAULT '0',
  `nota8` DOUBLE NULL DEFAULT '0',
  `nota9` DOUBLE NULL DEFAULT '0',
  `nota10` DOUBLE NULL DEFAULT '0',
  `recuperacion` DOUBLE NULL DEFAULT '0',
  `asistencia` INT NULL DEFAULT 0 ,
  `promedio` DOUBLE NULL DEFAULT '0',
  `fecha_ingreso` DATE NOT NULL,
  `fecha_modificacion` DATE NOT NULL,
  `id_alumno` INT(10) NOT NULL,
  `id_matricula` INT(11) NOT NULL,
  `id_materia` DOUBLE NOT NULL,
  `id_config_materia` INT(11) NOT NULL,
  `id_malla` INT(11) NOT NULL,
  `id_desc_materia` INT(11) NOT NULL,
  PRIMARY KEY (`id_nota`),
    FOREIGN KEY (`id_alumno`)
    REFERENCES `sismodes_1`.`maestro_alumno` (`id_alumno`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`id_matricula`)
    REFERENCES `sismodes_1`.`matricula` (`id_matricula`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`id_materia`)
    REFERENCES `sismodes_1`.`nombre_materia` (`id1_nombre_materia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`id_config_materia`)
    REFERENCES `sismodes_1`.`config_materia` (`id_config_materia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`id_malla`)
    REFERENCES `sismodes_1`.`malla` (`id_malla`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
    FOREIGN KEY (`id_desc_materia`)
    REFERENCES `sismodes_1`.`desc_materia` (`id_desc_materia`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = INNODB;

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
