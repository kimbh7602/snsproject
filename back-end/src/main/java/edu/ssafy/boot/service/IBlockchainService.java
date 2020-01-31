package edu.ssafy.boot.service;

import java.util.List;

import edu.ssafy.boot.dto.BlockVo;

public interface IBlockchainService {
	
	public void addBlock(BlockVo blk);

	public boolean isValid();

	public void displayChain();

	public List<BlockVo> getChain();
}
