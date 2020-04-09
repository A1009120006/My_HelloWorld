package com.demo.batch.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author Yanghu
 * @since 2020-03-29
 */
public class TestBatch extends Model<TestBatch> {

    private static final long serialVersionUID=1L;

    @TableField("TXN_DT")
    private String txnDt;

    @TableField("TXN_SN")
    private String txnSn;

    @TableField("CORE_TXN_DT")
    private String coreTxnDt;

    @TableField("CORE_TXN_SSN")
    private String coreTxnSsn;

    @TableField("TXN_AMT")
    private BigDecimal txnAmt;


    public String getTxnDt() {
        return txnDt;
    }

    public void setTxnDt(String txnDt) {
        this.txnDt = txnDt;
    }

    public String getTxnSn() {
        return txnSn;
    }

    public void setTxnSn(String txnSn) {
        this.txnSn = txnSn;
    }

    public String getCoreTxnDt() {
        return coreTxnDt;
    }

    public void setCoreTxnDt(String coreTxnDt) {
        this.coreTxnDt = coreTxnDt;
    }

    public String getCoreTxnSsn() {
        return coreTxnSsn;
    }

    public void setCoreTxnSsn(String coreTxnSsn) {
        this.coreTxnSsn = coreTxnSsn;
    }

    public BigDecimal getTxnAmt() {
        return txnAmt;
    }

    public void setTxnAmt(BigDecimal txnAmt) {
        this.txnAmt = txnAmt;
    }

    @Override
    protected Serializable pkVal() {
        return null;
    }

    @Override
    public String toString() {
        return "TestBatch{" +
        "txnDt=" + txnDt +
        ", txnSn=" + txnSn +
        ", coreTxnDt=" + coreTxnDt +
        ", coreTxnSsn=" + coreTxnSsn +
        ", txnAmt=" + txnAmt +
        "}";
    }
}
