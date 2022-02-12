import './App.css';
import { Card } from '../node_modules/primereact/card';
import CustomInput from './Component/CustomInput';
import CustomTable from './Component/CustomTable';
import { useState } from 'react';
import ApiService from './Services/api.service';

const App = () => {
  const [gamer, setGamer] = useState(null);
  const [tippResult, setTippResult] = useState(null);
  const [gameStat, setGameStat] = useState(null);
  const [display, setDisplay] = useState('game');
  const [statType, setStatType] = useState(null);
  const [gameStats, setGameStats] = useState([]);

  const gamerRegOrLog = (name) => {
    ApiService.regOrLog(name).then(
      (resreg) => {
        ApiService.startGame(resreg.data).then(
          (resStart) => {
            const _gamer = { ...resreg.data };
            _gamer.currentGameStatId = resStart.data;
            setGamer(_gamer);
          },
          (error) => {
            console.log('error', error);
          }
        );
      },
      (error) => {
        console.log('error', error);
      }
    );
  };
  const gamerTipp = (tipp) => {
    const _gamer = { ...gamer };
    _gamer.tipp = tipp;
    ApiService.tippNumber(_gamer).then(
      (res) => {
        const typeOfResData = typeof res.data;
        if (typeOfResData === 'object') {
          setTippResult('gameover');
          setGameStat(res.data);
        } else {
          setTippResult(res.data);
        }
      },
      (error) => {
        console.log('error', error);
      }
    );
  };

  const restartGame = () => {
    ApiService.startGame(gamer).then(
      (resStart) => {
        const _gamer = { ...gamer };
        _gamer.currentGameStatId = resStart.data;
        setGamer(_gamer);
        setTippResult(null);
      },
      (error) => {
        console.log('error', error);
      }
    );
  };
  const getGameStats = (id)=> {
      ApiService.getGameStats(id).then(
        (res) => {
          setGameStats(res.data);
        },
        (error) => {
          console.log('error', error);
        }
      );
  }
  return (
    <div className="App App-header">
      {display === 'game' ? (
        <CustomInput
          taskType={gamer ? 'tipp' : 'reglog'}
          gamerRegOrLog={gamerRegOrLog}
          gamerTipp={gamerTipp}
          tippResult={tippResult}
          setGamer={setGamer}
          setTippResult={setTippResult}
          setGameStat={setGameStat}
          restartGame={restartGame}
          gamer={gamer}
          gameStat={gameStat}
          setDisplay={setDisplay}
          setStatType={setStatType}
          getGameStats={getGameStats}
        />
      ) : (
        <CustomTable
          setDisplay={setDisplay}
          statType={statType}
          gamer={gamer}
          gameStats={gameStats}
          setGameStats={setGameStats}
        />
      )}
    </div>
  );
};

export default App;
