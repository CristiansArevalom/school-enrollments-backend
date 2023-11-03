package com.carevalom.school.school.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

/*NoRepositoryBean es una anotación que se utiliza para marcar una interfaz o una clase como una interfaz de repositorio personalizada que no debe ser considerada como un repositorio de Spring Data JPA.
Lo que significa que Spring Data JPA no generará automáticamente una implementación para ella. */

/*Se crea para  eliminar el acoplamiento del repository JPA */
/**No se añade la anotación repository porque al heredar de Jpa ya la tiene */
@NoRepositoryBean 
public interface IGenericRepository<T, ID> extends JpaRepository<T,ID>{
    
}
