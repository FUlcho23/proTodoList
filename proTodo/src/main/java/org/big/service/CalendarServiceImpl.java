package org.big.service;

import java.util.List;

import org.big.dto.TodoDto;
import org.big.mapper.CalenderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalendarServiceImpl implements CalendarService{
//sql-만들어야함 파이쟈~~
	@Autowired
	private CalenderMapper calenderMapper;
	
	
	@Override
	public List<TodoDto> selectTodo(String tdWorkM) {
		// TODO Auto-generated method stub
		return calenderMapper.selectTodo(tdWorkM);
	}

	@Override
	public void addTodo(TodoDto todo) throws Exception {
		calenderMapper.addTodo(todo);
	}

	@Override
	public void deleteTodo(String tdId) throws Exception {
		calenderMapper.deleteTodo(tdId);
	}

	@Override
	public void updateTodo(TodoDto todo) throws Exception {
		calenderMapper.updateTodo(todo);
		
	}

	@Override
	public String getTeamName(String tdWorkM) throws Exception {
		// TODO Auto-generated method stub
		return calenderMapper.getTeamName(tdWorkM);
	}

}
