package org.big.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface proTodoMapper {


   List<String> getFilePathsByBoardIdx(int boardIdx);

}