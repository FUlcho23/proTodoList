package org.big.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.big.dto.TodoDto;

@Mapper
public interface CalenderMapper {
    List<TodoDto> selectTodo() throws Exception;
    void addTodo(TodoDto todo) throws Exception;
    void deleteTodo(String tdId) throws Exception;
    void updateTodo(TodoDto todo) throws Exception;
}
