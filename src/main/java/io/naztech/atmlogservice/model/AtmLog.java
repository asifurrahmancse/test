package io.naztech.atmlogservice.model;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Collectors;

import lombok.Data;

@Data
public class AtmLog {

	private String postingDate;
	private String transactionDate;
	private String cardNo;
	private String accountNo;
	private String transactionName;
	private String terminalId;
	private String terminalLocation;
	private String retailerId;
	private String approval;
	private String mccCode;
	private String posCondition;
	private String rrn;
	private String stan;
	private String issuer;
	private String acquirer;
	private String txnAmountBdt;
	private String txnFeeAmountBdt;
	private String vatAmountBdt;
	private String issuerFeeBd;
	private String issuerVatBdt;
	private String totalAmountBdt;
	private String issuerIncomeBdt;
	private String acquirerIncomeBdt;
	private String itclIncomeBdt;
	private String bankProfitBdt;
	private String sourceFileData;//
	
	private static Map<String, String> rs2BeanMap;
	private static Map<String, String> sp2BeanMap;
	
	public static Map<String, String> getSp2BeanMap() {
		if (sp2BeanMap == null)
			sp2BeanMap = getRs2BeanMap().entrySet().stream()
					.collect(Collectors.toMap(e -> "@" + e.getKey(), e -> e.getValue()));
		return sp2BeanMap;
	}
	
	public static Map<String, String> getRs2BeanMap() {

		if (rs2BeanMap == null) {
			rs2BeanMap = new LinkedHashMap<String, String>();

			rs2BeanMap.put("dt_posting_date", "postingDate");
			rs2BeanMap.put("dt_transaction_date", "transactionDate");
			rs2BeanMap.put("tx_card_no", "cardNo");
			rs2BeanMap.put("tx_account_no", "accountNo");
			rs2BeanMap.put("tx_transaction_name", "transactionName");
			rs2BeanMap.put("tx_terminal_id", "terminalId");
			rs2BeanMap.put("tx_terminal_location", "terminalLocation");
			rs2BeanMap.put("tx_retailer_id", "retailerId");
			rs2BeanMap.put("tx_approval", "approval");
			rs2BeanMap.put("tx_mcc_code", "mccCode");
			rs2BeanMap.put("tx_pos_condition", "posCondition");
			rs2BeanMap.put("tx_rrn", "rrn");
			rs2BeanMap.put("tx_stan", "stan");
			rs2BeanMap.put("tx_issuer", "issuer");
			rs2BeanMap.put("tx_acquirer", "acquirer");
			rs2BeanMap.put("tx_txn_amount_bdt", "txnAmountBdt");
			rs2BeanMap.put("tx_txn_fee_amount_bdt", "txnFeeAmountBdt");
			rs2BeanMap.put("tx_vat_amount_bdt", "vatAmountBdt");
			rs2BeanMap.put("tx_issuer_fee_bdt", "issuerFeeBd");
			rs2BeanMap.put("tx_issuer_vat_bdt", "issuerVatBdt");
			rs2BeanMap.put("tx_total_amount_bdt", "totalAmountBdt");
			rs2BeanMap.put("tx_issuer_income_bdt", "issuerIncomeBdt");
			rs2BeanMap.put("tx_acquirer_income_bdt", "acquirerIncomeBdt");
			rs2BeanMap.put("tx_itcl_income_bdt", "itclIncomeBdt");
			rs2BeanMap.put("tx_bank_profit_bdt", "bankProfitBdt");
			rs2BeanMap.put("tx_source_file_data", "sourceFileData");//

		}
		return rs2BeanMap;
	}
	
	public Map<String, Object> getValueMap() {

		Map<String, Object> map = new LinkedHashMap<String, Object>();

		map.put("@posting_date", this.getPostingDate());
		map.put("@transaction_date", this.getTransactionDate());
		map.put("@card_no", this.getCardNo());
		map.put("@account_no", this.getAccountNo());
		map.put("@transaction_name", this.getTransactionName());
		map.put("@terminal_id", this.getTerminalId());
		map.put("@terminal_location", this.getTerminalLocation());
		map.put("@retailer_id", this.getRetailerId());
		map.put("@approval", this.getApproval());
		map.put("@mcc_code", this.getMccCode());
		map.put("@pos_condition", this.getPosCondition());
		map.put("@rrn", this.getRrn());
		map.put("@stan", this.getStan());
		map.put("@issuer", this.getIssuer());
		map.put("@acquirer", this.getAcquirer());
		map.put("@txn_amount_bdt", this.getTxnAmountBdt());
		map.put("@txn_ref_amount_bdt", this.getTxnFeeAmountBdt());
		map.put("@vat_amount_bdt", this.getVatAmountBdt());
		map.put("@issuer_fee_bdt", this.getIssuerFeeBd());
		map.put("@issuer_vat_bdt", this.getIssuerVatBdt());
		map.put("@total_amount_bdt", this.getTotalAmountBdt());
		map.put("@issuer_income_bdt", this.getIssuerIncomeBdt());
		map.put("@acquirer_income_bdt", this.getAcquirerIncomeBdt());
		map.put("@itcl_income_bdt", this.getItclIncomeBdt());
		map.put("@bank_profit_bdt", this.getBankProfitBdt());
		map.put("@source_file_data", this.getSourceFileData());//

		return map;
	}
	
}
