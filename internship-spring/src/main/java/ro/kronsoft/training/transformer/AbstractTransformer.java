package ro.kronsoft.training.transformer;

import java.util.ArrayList;
import java.util.List;

import ro.kronsoft.training.dto.BaseDto;
import ro.kronsoft.training.entities.BaseEntity;

public abstract class AbstractTransformer<T extends BaseEntity, X extends BaseDto> {

	public final List<X> toDtoList(List<T> entities) {
		List<X> dtoList = new ArrayList<>();
		for (T entity : entities) {
			dtoList.add(toDto(entity));
		}
		return dtoList;
//		return entities.stream().map(this::toDto).collect(Collectors.toList());
	}
	
	public abstract X toDto(T entity);

	public abstract T toEntity(X dto);

	protected abstract T findOrCreateNew(Long id);

}
