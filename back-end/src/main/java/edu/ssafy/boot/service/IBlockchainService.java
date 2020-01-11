package edu.ssafy.boot.service;

import edu.ssafy.boot.dto.BlockVo;

public interface IBlockchainService {
	
	public void addBlock(BlockVo blk);

	public boolean isValid();

	public void displayChain();
}
