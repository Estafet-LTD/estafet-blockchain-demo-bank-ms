package com.estafet.blockchain.demo.ms.bank.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "ACCOUNT")
public class Account {

	@Id
	@SequenceGenerator(name = "ACCOUNT_ID_SEQ", sequenceName = "ACCOUNT_ID_SEQ", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_SEQ")
	@Column(name = "ACCOUNT_ID")
	private Integer id;
	
	@Column(name = "WALLET_ID", nullable = false)
	private String walletId;
	
	@Column(name = "ACCOUNT_NAME", nullable = false)
	private String accountName;
	
	@Column(name = "PUBLIC_KEY", nullable = false)
	private String publicKey;
	
	@Transient
	private String version;
	
	@OneToMany(mappedBy = "transactionAccount", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private Set<Transaction> transactions = new HashSet<Transaction>();
	
	public double getBalance() {
		double balance = 0;
		for (Transaction transaction : transactions) {
			balance += transaction.getAmount();
		}
		return balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWalletId() {
		return walletId;
	}

	public void setWalletId(String walletId) {
		this.walletId = walletId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getPublicKey() {
		return publicKey;
	}

	public void setPublicKey(String publicKey) {
		this.publicKey = publicKey;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public Account addTransaction(Transaction transaction) {
		transaction.setTransactionAccount(this);
		transactions.add(transaction);
		return this;
	}

	public Set<Transaction> getTransactions() {
		return transactions;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}