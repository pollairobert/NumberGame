import React, { useState } from 'react';
import { InputText } from '../../node_modules/primereact/inputtext';
import { Button } from '../../node_modules/primereact/button';
import classNames from 'classnames';
import { Card } from '../../node_modules/primereact/card';

const CustomInput = (props) => {
  const {
    taskType,
    gamerRegOrLog,
    gamerTipp,
    tippResult,
    setGamer,
    setTippResult,
    setGameStat,
    restartGame,
    gamer,
    gameStat,
    setDisplay,
    setStatType,
    getGameStats
  } = props;
  const [inputValue, setInputValue] = useState('');
  const [notANumber, setNotANumber] = useState(false);
  const [wrongNumberRange, setWrongNumberRange] = useState(false);
  const [emptyField, setEmptyField] = useState(false);
  const onInputChange = (e, taskType) => {
    let val = (e.target && e.target.value) || '';
    if (val) {
      setEmptyField(false);
      setNotANumber(false);
      setWrongNumberRange(false);
    }
    setTippResult(null);
    if (taskType === 'tipp') {
      let numberInput = parseInt(val);
      if (!numberInput) {
        setNotANumber(true);
      } else if (numberInput < 1 || numberInput > 100) {
        setWrongNumberRange(true);
      } else {
        setNotANumber(false);
        setWrongNumberRange(false);
      }
    }
    setInputValue(val);
  };
  const onSubmit = () => {
    if (tippResult === 'gameover') {
      restartGame();
    } else {
      if (inputValue) {
        if (taskType === 'reglog') {
          gamerRegOrLog(inputValue);
        } else {
          gamerTipp(inputValue);
        }
        setEmptyField(false);
        setInputValue('');
      } else {
        setEmptyField(true);
        setTippResult(null);
      }
    }
  };

  const logout = () => {
    setGamer(null);
    setTippResult(null);
    setGameStat(null);
    setInputValue('');
    setEmptyField(false);
  };

  const showStat = (id, type) => {
    if (type) {
      setDisplay('own');
      setStatType('own');
      getGameStats(id);
    } else {
      setDisplay('all');
      setStatType('all');
      getGameStats();
    }
  };

  return (
    <Card
      title="Number Game"
      className="card"
      style={{
        width: '500px',
        height: '450px',
        marginBottom: '2em',
        colo: 'blue',
        fontSize: '36px',
        fontWeight: 'bold',
      }}
    >
      <div
        style={{
          lineHeight: '1',
          fontSize: '26px',
          marginTop: '20px',
          fontWeight: 'normal',
        }}
      >
        {!gamer ? 'Adj meg egy nevet' : 'Tippelj egy számot 1 és 100 között'}
        <div style={{ marginTop: '23px' }}>
          <InputText
            id="name"
            value={inputValue}
            onChange={(e) => onInputChange(e, taskType)}
            style={
              taskType === 'reglog'
                ? { height: '26px', width: '250px', fontSize: '18px' }
                : { height: '26px', width: '50px', fontSize: '18px' }
            }
            disabled={tippResult === 'gameover' ? true : false}
          />

          {emptyField && <div className="p-error">Nem lehet üres!</div>}
          {notANumber && (
            <div className="p-error">Csak számot adhatsz meg!</div>
          )}
          {wrongNumberRange && (
            <div className="p-error">Csak 1 és 100 közötti számot adj meg!</div>
          )}
          <div style={{ marginTop: '23px' }}>
            <Button
              label={
                taskType === 'reglog'
                  ? 'Belépés/Regisztráció'
                  : tippResult === 'gameover'
                  ? 'Újrakezdem'
                  : 'Tippelek'
              }
              onClick={onSubmit}
              style={
                taskType === 'reglog'
                  ? {
                      backgroundColor: '#9dd0f1',
                      height: '60px',
                      width: '200px',
                      fontSize: '18px',
                    }
                  : tippResult === 'gameover'
                  ? {
                      backgroundColor: '#25f012',
                      height: '50px',
                      width: '120px',
                      fontSize: '18px',
                      marginRight: '20px',
                    }
                  : {
                      backgroundColor: '#f3d62e',
                      height: '50px',
                      width: '110px',
                      fontSize: '18px',
                    }
              }
              disabled={notANumber || wrongNumberRange ? true : false}
            />
            {tippResult === 'gameover' && (
              <Button
                label="Kijelentkezek"
                onClick={logout}
                style={{
                  backgroundColor: '#f52525',
                  height: '50px',
                  width: '130px',
                  fontSize: '18px',
                  color: 'white',
                }}
              />
            )}
          </div>
        </div>
        {tippResult && tippResult !== 'gameover' ? (
          <div className="p-error">{tippResult}</div>
        ) : (
          tippResult === 'gameover' && (
            <>
              <div className="p-error" style={{ marginTop: '20px' }}>
                A játék végetért!
              </div>
              <div className="p-error">{`A generált szám: ${gameStat?.gameNumber}`}</div>
              <div className="p-error">{`${gameStat?.gameTime} mp alatt találtad el.`}</div>
            </>
          )
        )}
        <div style={{ marginTop: '23px' }}>
          {gamer && (
            <Button
              label="Statisztikád"
              onClick={(e) => showStat(gamer.id, 'own')}
              style={{
                backgroundColor: 'blue',
                height: '50px',
                width: '130px',
                fontSize: '18px',
                color: 'white',
                marginRight: '20px',
              }}
            />
          )}
          <Button
            label="Teljes lista"
            onClick={(e) => showStat()}
            style={{
              backgroundColor: 'blue',
              height: '50px',
              width: '130px',
              fontSize: '18px',
              color: 'white',
            }}
          />
        </div>
      </div>
    </Card>
  );
};

export default CustomInput;
