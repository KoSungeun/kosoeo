package com.study.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.study.springboot.dao.ITransaction3Dao;
import com.study.springboot.service.IBuyTicketService;

@Controller
public class MyController {
	
	@Autowired
	IBuyTicketService buyTicket;
	@Autowired
	TransactionTemplate transactionTemplate;
	@Autowired
	ITransaction3Dao transaction3;
	
	
	@RequestMapping("/")
	public @ResponseBody String root() throws Exception{
		return "Transaction Propagation";
	}
	
	
	@RequestMapping("/buy_ticket")
	public String buy_ticket() {
		return "buy_ticket";
	}
	
	@RequestMapping("/buy_ticket_card")
	public String buy_ticket_card(@RequestParam("consumerId") String consumerId,
								  @RequestParam("amount") String amount,
								  @RequestParam("error") String error,
								  Model model) {
		
		model.addAttribute("consumerId", consumerId);
		model.addAttribute("amount", amount);
		try {
			transactionTemplate.execute(new TransactionCallbackWithoutResult() {
				
				@Override
				protected void doInTransactionWithoutResult(TransactionStatus status) {
				
					int nResult = buyTicket.buy(consumerId, Integer.parseInt(amount), error);
					
					// 의도적 에러 발생
					if (error.equals("2")) {int n = 10 / 0;}
					
					transaction3.pay(consumerId, Integer.parseInt(amount));
					
				}
			});
		} catch (Exception e) {
			System.out.println("[Transaction Propagation #1] Rollback");
			return "buy_ticket_error";
		}

		return "buy_ticket_end";
		
		
	}
	


}
