package br.com.emanuelgabriel.lojawebapi.domain.mapper;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 
 * @author emanuel.sousa
 *
 */
public interface IGenericMapper {

	/**
	 * Responsável em converter um objeto para outro tipo
	 * 
	 * @param <T>
	 * @param obj
	 * @param clazz
	 * @return <T>
	 */
	<T> T paraObjeto(Object obj, Class<T> clazz);

	/**
	 * Responsável em converter uma lista de objeto
	 * 
	 * @param <T>
	 * @param list
	 * @param clazz
	 * @return <T>
	 */
	<T> List<T> paraLista(List<?> list, Class<T> clazz);

	<T> Page<T> paraPage(Pageable pageable, Page<T> pageClass);

}