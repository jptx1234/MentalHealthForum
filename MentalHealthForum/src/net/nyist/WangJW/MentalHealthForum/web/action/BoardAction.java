package net.nyist.WangJW.MentalHealthForum.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

import net.nyist.WangJW.MentalHealthForum.domain.Board;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;
import net.nyist.WangJW.MentalHealthForum.service.IBoardService;
import net.nyist.WangJW.MentalHealthForum.web.action.base.BaseAction;

@Controller("boardAction")
@Scope("prototype")
public class BoardAction extends BaseAction<Board> {

	@Resource
	private IBoardService boardService;
	
	public String findBoardById(){
		Board board = boardService.findById(model.getId());
		SimplePropertyPreFilter filter = new SimplePropertyPreFilter(Board.class, "id","name");
		String json = JSON.toJSONString(board,filter);
		responseJson(json);
		
		return NONE;
	}
}
