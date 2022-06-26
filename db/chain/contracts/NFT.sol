// SPDX-License-Identifier: MIT
pragma solidity ^0.8.4;

// ERC721.sol
import "@openzeppelin/contracts/token/ERC721/ERC721.sol";

// Counter.sol
import "@openzeppelin/contracts/utils/Counters.sol";
import "@openzeppelin/contracts/access/Ownable.sol";

contract NFT is ERC721, Ownable {
    using Counters for Counters.Counter;

    string public _baseTokenURI;

    Counters.Counter public _tokenIDCounter;
    mapping(uint256 => SNFT) internal _idToNFT;

    mapping(address => uint256) public whitelist;
    mapping(address => bool) public blacklist;
    mapping(address => uint256[]) public userNFTs;

    struct SNFT {
        uint256 id;
        address owner;
        uint256 hatID;
        uint256 eyeID;
        uint256 mouthID;
        uint256 bodyID;
        uint256 handID;
        uint256 petID;
    }

    event NFTCreated(SNFT _snft);
    event WhitelistAdded(address _whitelistedAddress, uint256 amount);
    event BlacklistAdded(address _blacklistedAddress);

    constructor(string memory baseTokenURI_) ERC721("DB_Project", "DBP") {
        _baseTokenURI = baseTokenURI_;
    }

    function _baseURI() internal view override returns (string memory) {
        return _baseTokenURI;
    }

    function createNFT(
        uint256 hatID,
        uint256 eyeID,
        uint256 mouthID,
        uint256 bodyID,
        uint256 handID,
        uint256 petID
    ) public payable {
        uint256 tokenId = _tokenIDCounter.current(); // Current token id for mint new one
        _mint(msg.sender, tokenId); // mint function for ERC721 contract

        // Create a memory variable for emit event and add to mapping
        SNFT memory snft = SNFT(
            tokenId,
            msg.sender,
            hatID,
            eyeID,
            mouthID,
            bodyID,
            handID,
            petID
        );

        // Add nft struct to mapping with tokenId as key
        _idToNFT[tokenId] = snft;

        // Add token id to user mapping array
        userNFTs[msg.sender].push(tokenId);
        // Emit event with nft struct as argument
        emit NFTCreated(snft);

        // Increment token id counter by 1 for next mint operation
        _tokenIDCounter.increment();
    }

    function addWhitelist(address _user, uint256 _amount) public {
        // Add user to whitelist mapping with amount as value
        whitelist[_user] = _amount;
        // Emit event with user address and amount as arguments
        emit WhitelistAdded(_user, _amount);
    }

    function addBlacklist(address _user) public {
        // Add user to blacklist mapping with true as value
        blacklist[_user] = true;
        // Emit event with user address as argument
        emit BlacklistAdded(_user);
    }

    function getUserNFTs() public view returns (uint256[] memory) {
        return userNFTs[msg.sender];
    }
}
