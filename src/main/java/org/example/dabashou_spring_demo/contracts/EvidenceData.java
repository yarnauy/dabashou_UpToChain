package org.example.dabashou_spring_demo.contracts;

import org.fisco.bcos.sdk.v3.client.Client;
import org.fisco.bcos.sdk.v3.codec.datatypes.*;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Bytes32;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.Uint256;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple2;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple3;
import org.fisco.bcos.sdk.v3.codec.datatypes.generated.tuples.generated.Tuple7;
import org.fisco.bcos.sdk.v3.contract.Contract;
import org.fisco.bcos.sdk.v3.crypto.CryptoSuite;
import org.fisco.bcos.sdk.v3.crypto.keypair.CryptoKeyPair;
import org.fisco.bcos.sdk.v3.model.CryptoType;
import org.fisco.bcos.sdk.v3.model.TransactionReceipt;
import org.fisco.bcos.sdk.v3.model.callback.CallCallback;
import org.fisco.bcos.sdk.v3.model.callback.TransactionCallback;
import org.fisco.bcos.sdk.v3.transaction.model.exception.ContractException;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@SuppressWarnings("unchecked")
public class EvidenceData extends Contract {
    public static final String[] BINARY_ARRAY = {"608060405234801561001057600080fd5b5061118f806100206000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c8063b2dfeebf11610066578063b2dfeebf14610127578063ba545e851461013a578063c2bb124a1461014d578063da0fd28614610162578063f49cb45a1461018357600080fd5b80634d3d096b146100985780635b6beeb9146100c05780637c8b397a146100e1578063b16c6ee714610101575b600080fd5b6100ab6100a6366004610cac565b610196565b60405190151581526020015b60405180910390f35b6100d36100ce366004610cac565b6101bf565b6040519081526020016100b7565b6100f46100ef366004610cac565b6101e9565b6040516100b79190610d45565b61011461010f366004610cac565b610318565b6040516100b79796959493929190610dcf565b6100d3610135366004610cac565b61057d565b6100ab610148366004610e9d565b6105a7565b61016061015b366004610f7e565b6105eb565b005b61017561017036600461104b565b6107a3565b6040516100b7929190611090565b6101606101913660046110ab565b610872565b600080826040516101a79190611102565b90815260405190819003602001902054151592915050565b600080826040516101d09190611102565b9081526020016040518091039020600201549050919050565b60606001826040516101fb9190611102565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b8282101561030d5783829060005260206000209060030201604051806060016040529081600082015481526020016001820180546102669061111e565b80601f01602080910402602001604051908101604052809291908181526020018280546102929061111e565b80156102df5780601f106102b4576101008083540402835291602001916102df565b820191906000526020600020905b8154815290600101906020018083116102c257829003601f168201915b5050509183525050600291909101546001600160a01b03166020918201529082526001929092019101610229565b505050509050919050565b600060606000806060806000806000896040516103359190611102565b908152602001604051809103902090508060000154816001018260020154836003015484600401856005018660060160009054906101000a90046001600160a01b03168580546103849061111e565b80601f01602080910402602001604051908101604052809291908181526020018280546103b09061111e565b80156103fd5780601f106103d2576101008083540402835291602001916103fd565b820191906000526020600020905b8154815290600101906020018083116103e057829003601f168201915b505050505095508280546104109061111e565b80601f016020809104026020016040519081016040528092919081815260200182805461043c9061111e565b80156104895780601f1061045e57610100808354040283529160200191610489565b820191906000526020600020905b81548152906001019060200180831161046c57829003601f168201915b5050505050925081805480602002602001604051908101604052809291908181526020016000905b8282101561055d5783829060005260206000200180546104d09061111e565b80601f01602080910402602001604051908101604052809291908181526020018280546104fc9061111e565b80156105495780601f1061051e57610100808354040283529160200191610549565b820191906000526020600020905b81548152906001019060200180831161052c57829003601f168201915b5050505050815260200190600101906104b1565b505050509150975097509750975097509750975050919395979092949650565b6000808260405161058e9190611102565b9081526020016040518091039020600301549050919050565b60006002836040516105b99190611102565b908152604080519182900360209081019092206001600160a01b0385166000908152925290205460ff16905092915050565b60008651116106375760405162461bcd60e51b8152602060048201526013602482015272195d9a59195b98d95251081c995c5d5a5c9959606a1b60448201526064015b60405180910390fd5b6000866040516106479190611102565b90815260405190819003602001902054156106a45760405162461bcd60e51b815260206004820152601760248201527f65766964656e636520616c726561647920657869737473000000000000000000604482015260640161062e565b6040518060e00160405280888152602001878152602001868152602001858152602001848152602001838152602001826001600160a01b03168152506000876040516106f09190611102565b9081526020016040518091039020600082015181600001556020820151816001019080519060200190610724929190610a9a565b50604082015160028201556060820151600382015560808201518051610754916004840191602090910190610a9a565b5060a08201518051610770916005840191602090910190610b1e565b5060c09190910151600690910180546001600160a01b0319166001600160a01b0390921691909117905550505050505050565b60006060600080856040516107b89190611102565b9081526040519081900360200190208054909150610804575050604080518082019091526012815271195d9a59195b98d9481b9bdd08199bdd5b9960721b60208201526000915061086b565b8381600201541461083f57505060408051808201909152600e81526d0d0c2e6d040dcdee840dac2e8c6d60931b60208201526000915061086b565b505060408051808201909152600e81526d195d9a59195b98d9481d985b1a5960921b6020820152600191505b9250929050565b6000826040516108829190611102565b908152604051908190036020019020546108d35760405162461bcd60e51b8152602060048201526012602482015271195d9a59195b98d9481b9bdd08199bdd5b9960721b604482015260640161062e565b6000826040516108e39190611102565b9081526020016040518091039020600301546001146109385760405162461bcd60e51b81526020600482015260116024820152706e6f74206d756c7469736967206d6f646560781b604482015260640161062e565b6002826040516109489190611102565b90815260408051602092819003830190206001600160a01b0384166000908152925290205460ff16156109b05760405162461bcd60e51b815260206004820152601060248201526f185b1c9958591e48185c1c1c9bdd995960821b604482015260640161062e565b6001826040516109c09190611102565b90815260408051602092819003830181206060820183528682528382018681526001600160a01b038616938301939093528054600180820183556000928352918590208351600390920201908155925180519294610a249392850192910190610a9a565b50604091820151600291820180546001600160a01b0319166001600160a01b03909216919091179055905160019190610a5e908590611102565b90815260408051602092819003830190206001600160a01b0394909416600090815293909152909120805460ff19169115159190911790555050565b828054610aa69061111e565b90600052602060002090601f016020900481019282610ac85760008555610b0e565b82601f10610ae157805160ff1916838001178555610b0e565b82800160010185558215610b0e579182015b82811115610b0e578251825591602001919060010190610af3565b50610b1a929150610b77565b5090565b828054828255906000526020600020908101928215610b6b579160200282015b82811115610b6b5782518051610b5b918491602090910190610a9a565b5091602001919060010190610b3e565b50610b1a929150610b8c565b5b80821115610b1a5760008155600101610b78565b80821115610b1a576000610ba08282610ba9565b50600101610b8c565b508054610bb59061111e565b6000825580601f10610bc5575050565b601f016020900490600052602060002090810190610be39190610b77565b50565b634e487b7160e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715610c2557610c25610be6565b604052919050565b600067ffffffffffffffff831115610c4757610c47610be6565b610c5a601f8401601f1916602001610bfc565b9050828152838383011115610c6e57600080fd5b828260208301376000602084830101529392505050565b600082601f830112610c9657600080fd5b610ca583833560208501610c2d565b9392505050565b600060208284031215610cbe57600080fd5b813567ffffffffffffffff811115610cd557600080fd5b610ce184828501610c85565b949350505050565b60005b83811015610d04578181015183820152602001610cec565b83811115610d13576000848401525b50505050565b60008151808452610d31816020860160208601610ce9565b601f01601f19169290920160200192915050565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b83811015610dc157603f19898403018552815160608151855288820151818a870152610d9a82870182610d19565b928901516001600160a01b0316958901959095525094870194925090860190600101610d6c565b509098975050505050505050565b8781526000602060e081840152610de960e084018a610d19565b8860408501528760608501528381036080850152610e078188610d19565b905083810360a08501528086518083528383019150838160051b84010184890160005b83811015610e5857601f19868403018552610e46838351610d19565b94870194925090860190600101610e2a565b50506001600160a01b03881660c08801529450610e759350505050565b98975050505050505050565b80356001600160a01b0381168114610e9857600080fd5b919050565b60008060408385031215610eb057600080fd5b823567ffffffffffffffff811115610ec757600080fd5b610ed385828601610c85565b925050610ee260208401610e81565b90509250929050565b600082601f830112610efc57600080fd5b8135602067ffffffffffffffff80831115610f1957610f19610be6565b8260051b610f28838201610bfc565b9384528581018301938381019088861115610f4257600080fd5b84880192505b85831015610e7557823584811115610f605760008081fd5b610f6e8a87838c0101610c85565b8352509184019190840190610f48565b600080600080600080600060e0888a031215610f9957600080fd5b87359650602088013567ffffffffffffffff80821115610fb857600080fd5b610fc48b838c01610c85565b975060408a0135965060608a0135955060808a0135915080821115","610fe857600080fd5b818a0191508a601f830112610ffc57600080fd5b61100b8b833560208501610c2d565b945060a08a013591508082111561102157600080fd5b5061102e8a828b01610eeb565b92505061103d60c08901610e81565b905092959891949750929550565b6000806040838503121561105e57600080fd5b823567ffffffffffffffff81111561107557600080fd5b61108185828601610c85565b95602094909401359450505050565b8215158152604060208201526000610ce16040830184610d19565b6000806000606084860312156110c057600080fd5b83359250602084013567ffffffffffffffff8111156110de57600080fd5b6110ea86828701610c85565b9250506110f960408501610e81565b90509250925092565b60008251611114818460208701610ce9565b9190910192915050565b600181811c9082168061113257607f821691505b6020821081141561115357634e487b7160e01b600052602260045260246000fd5b5091905056fea264697066735822122095e99f87e51eb8cdf99fa6b0f62cba064aeaac28d6e1f50d44e24b211530487164736f6c634300080b0033"};

    public static final String BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", BINARY_ARRAY);

    public static final String[] SM_BINARY_ARRAY = {"608060405234801561001057600080fd5b50611194806100206000396000f3fe608060405234801561001057600080fd5b50600436106100935760003560e01c8063879e96c811610066578063879e96c8146101095780639c955c981461011c578063c60a1d361461013c578063cf24b50114610162578063e969fc8a1461017557600080fd5b806312655526146100985780634684ece8146100ad5780634e7dbad5146100d3578063568fe885146100f6575b600080fd5b6100ab6100a6366004610ccd565b610196565b005b6100c06100bb366004610d24565b6103c6565b6040519081526020015b60405180910390f35b6100e66100e1366004610d61565b6103f0565b60405190151581526020016100ca565b6100e6610104366004610d24565b610434565b6100ab610117366004610e4e565b61045d565b61012f61012a366004610d24565b610612565b6040516100ca9190610f77565b61014f61014a366004610d24565b610741565b6040516100ca9796959493929190611001565b6100c0610170366004610d24565b6109a6565b6101886101833660046110a7565b6109d0565b6040516100ca9291906110ec565b6000826040516101a69190611107565b908152604051908190036020019020546101fd57604051636381e58960e11b8152602060048201526012602482015271195d9a59195b98d9481b9bdd08199bdd5b9960721b60448201526064015b60405180910390fd5b60008260405161020d9190611107565b90815260200160405180910390206003015460011461026357604051636381e58960e11b81526020600482015260116024820152706e6f74206d756c7469736967206d6f646560781b60448201526064016101f4565b6002826040516102739190611107565b90815260408051602092819003830190206001600160a01b0384166000908152925290205460ff16156102dc57604051636381e58960e11b815260206004820152601060248201526f185b1c9958591e48185c1c1c9bdd995960821b60448201526064016101f4565b6001826040516102ec9190611107565b90815260408051602092819003830181206060820183528682528382018681526001600160a01b0386169383019390935280546001808201835560009283529185902083516003909202019081559251805192946103509392850192910190610a9f565b50604091820151600291820180546001600160a01b0319166001600160a01b0390921691909117905590516001919061038a908590611107565b90815260408051602092819003830190206001600160a01b0394909416600090815293909152909120805460ff19169115159190911790555050565b600080826040516103d79190611107565b9081526020016040518091039020600201549050919050565b60006002836040516104029190611107565b908152604080519182900360209081019092206001600160a01b0385166000908152925290205460ff16905092915050565b600080826040516104459190611107565b90815260405190819003602001902054151592915050565b60008651116104a557604051636381e58960e11b8152602060048201526013602482015272195d9a59195b98d95251081c995c5d5a5c9959606a1b60448201526064016101f4565b6000866040516104b59190611107565b908152604051908190036020019020541561051357604051636381e58960e11b815260206004820152601760248201527f65766964656e636520616c72656164792065786973747300000000000000000060448201526064016101f4565b6040518060e00160405280888152602001878152602001868152602001858152602001848152602001838152602001826001600160a01b031681525060008760405161055f9190611107565b9081526020016040518091039020600082015181600001556020820151816001019080519060200190610593929190610a9f565b506040820151600282015560608201516003820155608082015180516105c3916004840191602090910190610a9f565b5060a082015180516105df916005840191602090910190610b23565b5060c09190910151600690910180546001600160a01b0319166001600160a01b0390921691909117905550505050505050565b60606001826040516106249190611107565b9081526020016040518091039020805480602002602001604051908101604052809291908181526020016000905b8282101561073657838290600052602060002090600302016040518060600160405290816000820154815260200160018201805461068f90611123565b80601f01602080910402602001604051908101604052809291908181526020018280546106bb90611123565b80156107085780601f106106dd57610100808354040283529160200191610708565b820191906000526020600020905b8154815290600101906020018083116106eb57829003601f168201915b5050509183525050600291909101546001600160a01b03166020918201529082526001929092019101610652565b505050509050919050565b6000606060008060608060008060008960405161075e9190611107565b908152602001604051809103902090508060000154816001018260020154836003015484600401856005018660060160009054906101000a90046001600160a01b03168580546107ad90611123565b80601f01602080910402602001604051908101604052809291908181526020018280546107d990611123565b80156108265780601f106107fb57610100808354040283529160200191610826565b820191906000526020600020905b81548152906001019060200180831161080957829003601f168201915b5050505050955082805461083990611123565b80601f016020809104026020016040519081016040528092919081815260200182805461086590611123565b80156108b25780601f10610887576101008083540402835291602001916108b2565b820191906000526020600020905b81548152906001019060200180831161089557829003601f168201915b5050505050925081805480602002602001604051908101604052809291908181526020016000905b828210156109865783829060005260206000200180546108f990611123565b80601f016020809104026020016040519081016040528092919081815260200182805461092590611123565b80156109725780601f1061094757610100808354040283529160200191610972565b820191906000526020600020905b81548152906001019060200180831161095557829003601f168201915b5050505050815260200190600101906108da565b505050509150975097509750975097509750975050919395979092949650565b600080826040516109b79190611107565b9081526020016040518091039020600301549050919050565b60006060600080856040516109e59190611107565b9081526040519081900360200190208054909150610a31575050604080518082019091526012815271195d9a59195b98d9481b9bdd08199bdd5b9960721b602082015260009150610a98565b83816002015414610a6c57505060408051808201909152600e81526d0d0c2e6d040dcdee840dac2e8c6d60931b602082015260009150610a98565b505060408051808201909152600e81526d195d9a59195b98d9481d985b1a5960921b6020820152600191505b9250929050565b828054610aab90611123565b90600052602060002090601f016020900481019282610acd5760008555610b13565b82601f10610ae657805160ff1916838001178555610b13565b82800160010185558215610b13579182015b82811115610b13578251825591602001919060010190610af8565b50610b1f929150610b7c565b5090565b828054828255906000526020600020908101928215610b70579160200282015b82811115610b705782518051610b60918491602090910190610a9f565b5091602001919060010190610b43565b50610b1f929150610b91565b5b80821115610b1f5760008155600101610b7d565b80821115610b1f576000610ba58282610bae565b50600101610b91565b508054610bba90611123565b6000825580601f10610bca575050565b601f016020900490600052602060002090810190610be89190610b7c565b50565b63b95aa35560e01b600052604160045260246000fd5b604051601f8201601f1916810167ffffffffffffffff81118282101715610c2a57610c2a610beb565b604052919050565b600067ffffffffffffffff831115610c4c57610c4c610beb565b610c5f601f8401601f1916602001610c01565b9050828152838383011115610c7357600080fd5b828260208301376000602084830101529392505050565b600082601f830112610c9b57600080fd5b610caa83833560208501610c32565b9392505050565b80356001600160a01b0381168114610cc857600080fd5b919050565b600080600060608486031215610ce257600080fd5b83359250602084013567ffffffffffffffff811115610d0057600080fd5b610d0c86828701610c8a565b925050610d1b60408501610cb1565b90509250925092565b600060208284031215610d3657600080fd5b813567ffffffffffffffff811115610d4d57600080fd5b610d5984828501610c8a565b949350505050565b60008060408385031215610d7457600080fd5b823567ffffffffffffffff811115610d8b57600080fd5b610d9785828601610c8a565b925050610da660208401610cb1565b90509250929050565b600082601f830112610dc057600080fd5b8135602067ffffffffffffffff80831115610ddd57610ddd610beb565b8260051b610dec838201610c01565b9384528581018301938381019088861115610e0657600080fd5b84880192505b85831015610e4257823584811115610e245760008081fd5b610e328a87838c0101610c8a565b8352509184019190840190610e0c565b98975050505050505050565b600080600080600080600060e0888a031215610e6957600080fd5b87359650602088013567ffffffffffffffff80821115610e8857600080fd5b610e948b838c01610c8a565b975060408a0135965060608a0135955060808a0135915080821115610eb857600080fd5b818a0191508a601f830112610ecc57600080fd5b610edb8b833560208501610c32565b945060a08a0135915080821115610ef157600080fd5b50610efe8a828b01610daf565b925050610f0d60c08901610cb1565b905092959891949750929550565b60005b83811015610f36578181015183820152602001610f1e565b83811115610f45576000848401525b50505050565b60008151808452610f63816020860160208601610f1b565b601f01601f19169290920160200192915050565b60006020808301818452808551808352604092508286019150828160051b87010184880160005b83811015610ff357603f19898403018552815160608151855288820151818a870152610fcc82870182610f4b565b928901516001600160a01b0316958901959095","525094870194925090860190600101610f9e565b509098975050505050505050565b8781526000602060e08184015261101b60e084018a610f4b565b88604085015287606085015283810360808501526110398188610f4b565b905083810360a08501528086518083528383019150838160051b84010184890160005b8381101561108a57601f19868403018552611078838351610f4b565b9487019492509086019060010161105c565b50506001600160a01b03881660c08801529450610e429350505050565b600080604083850312156110ba57600080fd5b823567ffffffffffffffff8111156110d157600080fd5b6110dd85828601610c8a565b95602094909401359450505050565b8215158152604060208201526000610d596040830184610f4b565b60008251611119818460208701610f1b565b9190910192915050565b600181811c9082168061113757607f821691505b602082108114156111585763b95aa35560e01b600052602260045260246000fd5b5091905056fea2646970667358221220597f656fcbaf13f54b1c2a203127d7cf28bd3d5e14c278c8a40967848af77dad64736f6c634300080b0033"};

    public static final String SM_BINARY = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", SM_BINARY_ARRAY);

    public static final String[] ABI_ARRAY = {"[{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"timestamp\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"},{\"internalType\":\"address\",\"name\":\"approver\",\"type\":\"address\"}],\"name\":\"approveEvidence\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"uint256\",\"name\":\"timestamp\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"},{\"internalType\":\"bytes32\",\"name\":\"contentHash\",\"type\":\"bytes32\"},{\"internalType\":\"uint256\",\"name\":\"mode\",\"type\":\"uint256\"},{\"internalType\":\"bytes\",\"name\":\"reserved1\",\"type\":\"bytes\"},{\"internalType\":\"string[]\",\"name\":\"reserved2\",\"type\":\"string[]\"},{\"internalType\":\"address\",\"name\":\"creator\",\"type\":\"address\"}],\"name\":\"createEvidence\",\"outputs\":[],\"stateMutability\":\"nonpayable\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"}],\"name\":\"getApprovals\",\"outputs\":[{\"components\":[{\"internalType\":\"uint256\",\"name\":\"timestamp\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"},{\"internalType\":\"address\",\"name\":\"approver\",\"type\":\"address\"}],\"internalType\":\"struct EvidenceData.Approval[]\",\"name\":\"\",\"type\":\"tuple[]\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"}],\"name\":\"getEvidence\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"},{\"internalType\":\"string\",\"name\":\"\",\"type\":\"string\"},{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"},{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"},{\"internalType\":\"bytes\",\"name\":\"\",\"type\":\"bytes\"},{\"internalType\":\"string[]\",\"name\":\"\",\"type\":\"string[]\"},{\"internalType\":\"address\",\"name\":\"\",\"type\":\"address\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"}],\"name\":\"getHash\",\"outputs\":[{\"internalType\":\"bytes32\",\"name\":\"\",\"type\":\"bytes32\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"}],\"name\":\"getMode\",\"outputs\":[{\"internalType\":\"uint256\",\"name\":\"\",\"type\":\"uint256\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"},{\"internalType\":\"address\",\"name\":\"approver\",\"type\":\"address\"}],\"name\":\"isApproved\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"}],\"name\":\"isExist\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"\",\"type\":\"bool\"}],\"stateMutability\":\"view\",\"type\":\"function\"},{\"inputs\":[{\"internalType\":\"string\",\"name\":\"evidenceID\",\"type\":\"string\"},{\"internalType\":\"bytes32\",\"name\":\"expectedHash\",\"type\":\"bytes32\"}],\"name\":\"verifyEvidence\",\"outputs\":[{\"internalType\":\"bool\",\"name\":\"isValid\",\"type\":\"bool\"},{\"internalType\":\"string\",\"name\":\"message\",\"type\":\"string\"}],\"stateMutability\":\"view\",\"type\":\"function\"}]"};

    public static final String ABI = org.fisco.bcos.sdk.v3.utils.StringUtils.joinAll("", ABI_ARRAY);

    public static final String FUNC_APPROVEEVIDENCE = "approveEvidence";

    public static final String FUNC_CREATEEVIDENCE = "createEvidence";

    public static final String FUNC_GETAPPROVALS = "getApprovals";

    public static final String FUNC_GETEVIDENCE = "getEvidence";

    public static final String FUNC_GETHASH = "getHash";

    public static final String FUNC_GETMODE = "getMode";

    public static final String FUNC_ISAPPROVED = "isApproved";

    public static final String FUNC_ISEXIST = "isExist";

    public static final String FUNC_VERIFYEVIDENCE = "verifyEvidence";

    protected EvidenceData(String contractAddress, Client client, CryptoKeyPair credential) {
        super(getBinary(client.getCryptoSuite()), contractAddress, client, credential);
    }

    public static String getBinary(CryptoSuite cryptoSuite) {
        return (cryptoSuite.getCryptoTypeConfig() == CryptoType.ECDSA_TYPE ? BINARY : SM_BINARY);
    }

    public static String getABI() {
        return ABI;
    }

    public TransactionReceipt approveEvidence(BigInteger timestamp, String evidenceID,
            String approver) {
        final Function function = new Function(
                FUNC_APPROVEEVIDENCE, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Utf8String(evidenceID),
                new Address(approver)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForApproveEvidence(BigInteger timestamp, String evidenceID,
            String approver) {
        final Function function = new Function(
                FUNC_APPROVEEVIDENCE, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Utf8String(evidenceID),
                new Address(approver)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String approveEvidence(BigInteger timestamp, String evidenceID, String approver,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_APPROVEEVIDENCE, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Utf8String(evidenceID),
                new Address(approver)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple3<BigInteger, String, String> getApproveEvidenceInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_APPROVEEVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple3<BigInteger, String, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (String) results.get(2).getValue()
                );
    }

    public TransactionReceipt createEvidence(BigInteger timestamp, String evidenceID,
            byte[] contentHash, BigInteger mode, byte[] reserved1, List<String> reserved2,
            String creator) {
        final Function function = new Function(
                FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Utf8String(evidenceID),
                new Bytes32(contentHash),
                new Uint256(mode),
                new DynamicBytes(reserved1),
                new DynamicArray<Utf8String>(
                        Utf8String.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(reserved2, Utf8String.class)),
                new Address(creator)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return executeTransaction(function);
    }

    public String getSignedTransactionForCreateEvidence(BigInteger timestamp, String evidenceID,
            byte[] contentHash, BigInteger mode, byte[] reserved1, List<String> reserved2,
            String creator) {
        final Function function = new Function(
                FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Utf8String(evidenceID),
                new Bytes32(contentHash),
                new Uint256(mode),
                new DynamicBytes(reserved1),
                new DynamicArray<Utf8String>(
                        Utf8String.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(reserved2, Utf8String.class)),
                new Address(creator)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return createSignedTransaction(function);
    }

    public String createEvidence(BigInteger timestamp, String evidenceID, byte[] contentHash,
            BigInteger mode, byte[] reserved1, List<String> reserved2, String creator,
            TransactionCallback callback) {
        final Function function = new Function(
                FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(new Uint256(timestamp),
                new Utf8String(evidenceID),
                new Bytes32(contentHash),
                new Uint256(mode),
                new DynamicBytes(reserved1),
                new DynamicArray<Utf8String>(
                        Utf8String.class,
                        org.fisco.bcos.sdk.v3.codec.Utils.typeMap(reserved2, Utf8String.class)),
                new Address(creator)),
                Collections.<TypeReference<?>>emptyList(), 0);
        return asyncExecuteTransaction(function, callback);
    }

    public Tuple7<BigInteger, String, byte[], BigInteger, byte[], List<String>, String> getCreateEvidenceInput(
            TransactionReceipt transactionReceipt) {
        String data = transactionReceipt.getInput().substring(10);
        final Function function = new Function(FUNC_CREATEEVIDENCE, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<Address>() {}));
        List<Type> results = this.functionReturnDecoder.decode(data, function.getOutputParameters());
        return new Tuple7<BigInteger, String, byte[], BigInteger, byte[], List<String>, String>(

                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (byte[]) results.get(4).getValue(), 
                convertToNative((List<Utf8String>) results.get(5).getValue()), 
                (String) results.get(6).getValue()
                );
    }

    public List<Approval> getApprovals(String evidenceID) throws ContractException {
        final Function function = new Function(FUNC_GETAPPROVALS, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Approval>>() {}));
        return executeCallWithSingleValueReturn(function, List.class);
    }

    public void getApprovals(String evidenceID, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETAPPROVALS, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<DynamicArray<Approval>>() {}));
        asyncExecuteCall(function, callback);
    }

    public Tuple7<BigInteger, String, byte[], BigInteger, byte[], List<String>, String> getEvidence(
            String evidenceID) throws ContractException {
        final Function function = new Function(FUNC_GETEVIDENCE, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<Address>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple7<BigInteger, String, byte[], BigInteger, byte[], List<String>, String>(
                (BigInteger) results.get(0).getValue(), 
                (String) results.get(1).getValue(), 
                (byte[]) results.get(2).getValue(), 
                (BigInteger) results.get(3).getValue(), 
                (byte[]) results.get(4).getValue(), 
                convertToNative((List<Utf8String>) results.get(5).getValue()), 
                (String) results.get(6).getValue());
    }

    public void getEvidence(String evidenceID, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETEVIDENCE, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Bytes32>() {}, new TypeReference<Uint256>() {}, new TypeReference<DynamicBytes>() {}, new TypeReference<DynamicArray<Utf8String>>() {}, new TypeReference<Address>() {}));
        asyncExecuteCall(function, callback);
    }

    public byte[] getHash(String evidenceID) throws ContractException {
        final Function function = new Function(FUNC_GETHASH, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        return executeCallWithSingleValueReturn(function, byte[].class);
    }

    public void getHash(String evidenceID, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETHASH, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bytes32>() {}));
        asyncExecuteCall(function, callback);
    }

    public BigInteger getMode(String evidenceID) throws ContractException {
        final Function function = new Function(FUNC_GETMODE, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeCallWithSingleValueReturn(function, BigInteger.class);
    }

    public void getMode(String evidenceID, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_GETMODE, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        asyncExecuteCall(function, callback);
    }

    public Boolean isApproved(String evidenceID, String approver) throws ContractException {
        final Function function = new Function(FUNC_ISAPPROVED, 
                Arrays.<Type>asList(new Utf8String(evidenceID),
                new Address(approver)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public void isApproved(String evidenceID, String approver, CallCallback callback) throws
            ContractException {
        final Function function = new Function(FUNC_ISAPPROVED, 
                Arrays.<Type>asList(new Utf8String(evidenceID),
                new Address(approver)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        asyncExecuteCall(function, callback);
    }

    public Boolean isExist(String evidenceID) throws ContractException {
        final Function function = new Function(FUNC_ISEXIST, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        return executeCallWithSingleValueReturn(function, Boolean.class);
    }

    public void isExist(String evidenceID, CallCallback callback) throws ContractException {
        final Function function = new Function(FUNC_ISEXIST, 
                Arrays.<Type>asList(new Utf8String(evidenceID)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}));
        asyncExecuteCall(function, callback);
    }

    public Tuple2<Boolean, String> verifyEvidence(String evidenceID, byte[] expectedHash) throws
            ContractException {
        final Function function = new Function(FUNC_VERIFYEVIDENCE, 
                Arrays.<Type>asList(new Utf8String(evidenceID),
                new Bytes32(expectedHash)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        List<Type> results = executeCallWithMultipleValueReturn(function);
        return new Tuple2<Boolean, String>(
                (Boolean) results.get(0).getValue(), 
                (String) results.get(1).getValue());
    }

    public void verifyEvidence(String evidenceID, byte[] expectedHash, CallCallback callback) throws
            ContractException {
        final Function function = new Function(FUNC_VERIFYEVIDENCE, 
                Arrays.<Type>asList(new Utf8String(evidenceID),
                new Bytes32(expectedHash)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Bool>() {}, new TypeReference<Utf8String>() {}));
        asyncExecuteCall(function, callback);
    }

    public static EvidenceData load(String contractAddress, Client client,
            CryptoKeyPair credential) {
        return new EvidenceData(contractAddress, client, credential);
    }

    public static EvidenceData deploy(Client client, CryptoKeyPair credential) throws
            ContractException {
        return deploy(EvidenceData.class, client, credential, getBinary(client.getCryptoSuite()), getABI(), null, null);
    }

    public static class Approval extends DynamicStruct {
        public BigInteger timestamp;

        public String evidenceID;

        public String approver;

        public Approval(Uint256 timestamp, Utf8String evidenceID, Address approver) {
            super(timestamp,evidenceID,approver);
            this.timestamp = timestamp.getValue();
            this.evidenceID = evidenceID.getValue();
            this.approver = approver.getValue();
        }

        public Approval(BigInteger timestamp, String evidenceID, String approver) {
            super(new Uint256(timestamp),new Utf8String(evidenceID),new Address(approver));
            this.timestamp = timestamp;
            this.evidenceID = evidenceID;
            this.approver = approver;
        }
    }
}
