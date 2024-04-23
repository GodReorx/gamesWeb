package com.games.model.services.converter;

import com.games.model.document.DiceGame;
import com.games.model.document.RankingDice;
import com.games.model.dto.DiceGameDTO;
import com.games.model.dto.PlayerDTO;
import com.games.model.dto.RankingDiceDTO;
import com.games.model.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class DtoConverter {

    public static PlayerDTO newPlayerToDTO (Player player){
        return new PlayerDTO(player.getId(), player.getEmail(), player.getNickname(), player.getPassword(), player.getRegistrationDate());
    }

    public static PlayerDTO playerToDTO (Player player, List<DiceGameDTO> diceGameDTOList){
        return new PlayerDTO(player.getId(), player.getEmail(), player.getNickname(), player.getPassword(), player.getRegistrationDate(),diceGameDTOList);
    }

    public static DiceGameDTO diceGameToDTO (DiceGame diceGame){
        return new DiceGameDTO(diceGame.getId(), diceGame.getIdPlayer(),diceGame.getDice1(), diceGame.getDice2());
    }

    public static List<DiceGameDTO> diceGameDTOList (List<DiceGame> diceGameList){
        List<DiceGameDTO> diceGameDTOList = new ArrayList<>();
        for (DiceGame diceGame : diceGameList){
            diceGameDTOList.add(diceGameToDTO(diceGame));
        }
        return diceGameDTOList;
    }

    public static RankingDiceDTO rankingDiceToDTO(RankingDice rankingDice){
        return new RankingDiceDTO(rankingDice.getIdPlayer(), rankingDice.getNamePlayer(), rankingDice.getSuccessPercentage());
    }

    public static List<RankingDiceDTO> rankingDiceDTOList(List<RankingDice> rankingListDB){
        List<RankingDiceDTO> rankingDiceListDTO = new ArrayList<>();
        for(RankingDice rankingDice : rankingListDB){
            rankingDiceListDTO.add(rankingDiceToDTO(rankingDice));
        }
        return rankingDiceListDTO;
    }
}
