package org.big.service;

import java.util.List;

import org.big.dto.TodoDto;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService{
//sql-만들어야함 파이쟈~~
	@Override
	public List<TodoDto> selectTodo() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addTodo(TodoDto todo) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteTodo(String tdId) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateTodo(TodoDto todo) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
