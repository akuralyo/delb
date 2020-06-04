package com.kreative.delb.common.utils;

public interface Transformer<T, V> {

	V mapToDto(T source);

	T mapToModel(V source);
}
