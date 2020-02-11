package edu.ssafy.boot.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.ssafy.boot.dto.BlockVo;

@Service("BlockchainService")
public class BlockchainService implements IBlockchainService {

	
	private List<BlockVo> chain;
	
	public BlockchainService() {
		chain = new ArrayList<BlockVo>();
		chain.add(generateGenesis());
	}
	
	public List<BlockVo> getChain() {
		return chain;
	}

	//첫 블록생성
	private BlockVo generateGenesis() {
		BlockVo genesis = new BlockVo(null);
		genesis.setPrehash(null);
		genesis.computeHash();
		return genesis;
	}
	
	//블록생성 추가
	public void addBlock(BlockVo blk) {
		BlockVo newBlock = blk;
		newBlock.setPrehash(chain.get(chain.size() - 1).getHash());
		newBlock.computeHash();
		this.chain.add(newBlock);
	}
	
	//블록 확인용
	public void displayChain() {
		for (int i = 0; i < chain.size(); i++) {
			System.out.println("Blcok: " + i);
			System.out.println("Hash: " + chain.get(i).getHash());
			System.out.println("PreHash: " + chain.get(i).getPrehash());
			System.out.println("Data: " + chain.get(i).getData());
			System.out.println("Date: " + chain.get(i).getTimestamp());
		}
	}
	
	//블록신뢰성 확인
	public boolean isValid() {

		for (int i = chain.size() - 1; i > 0; i--) {
			if (!(chain.get(i).getHash().equals(chain.get(i).computeHash()))) {
				System.out.println("유효하지않은 체인");
				return false;
			}
			if (!(chain.get(i).getPrehash().equals(chain.get(i - 1).computeHash()))) {
				System.out.println("유효하지않은 체인");
				return false;
			}

		}
		System.out.println("유효한 체인");
		return true;
	}

}
