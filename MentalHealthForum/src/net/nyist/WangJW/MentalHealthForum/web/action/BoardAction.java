package net.nyist.WangJW.MentalHealthForum.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import net.nyist.WangJW.MentalHealthForum.domain.Board;
import net.nyist.WangJW.MentalHealthForum.domain.Topic;
import net.nyist.WangJW.MentalHealthForum.service.IBoardService;
import net.nyist.WangJW.MentalHealthForum.web.action.base.BaseAction;

@Controller("boardAction")
@Scope("prototype")
public class BoardAction extends BaseAction<Board> {

	@Resource
	private IBoardService boardService;
	
	private Integer page = 1;
	private final Integer DEFAULT_PAGE_SIZE = 10; 
	
	public void setPage(Integer page){
		this.page = page;
	}
	
}
