import React from 'react';
import { Card } from '../../node_modules/primereact/card';
import { Button } from '../../node_modules/primereact/button';
import { DataTable } from '../../node_modules/primereact/datatable';
import { Column } from '../../node_modules/primereact/column';

const CustomTable = (props) => {
  const { setDisplay, statType, gameStats, setGameStats/* , gamer */} = props;

  const goBack = () => {
    setDisplay('game');
    setGameStats([]);
  };

  const bodyTemplate = (row) => {
    const date = new Date(row.startTime).toLocaleString();
    return date;
  };
  return (
    <Card
      title={statType === 'all' ? 'Teljes lista' : 'Saját statisztika'}
      className="card"
      style={{
        width: '1000px',
        height: '800px',
        marginBottom: '2em',
        colo: 'blue',
        fontSize: '36px',
        fontWeight: 'bold',
      }}
    >
      <div style={{ marginTop: '23px' }}>
        <Button
          label="Visszalépek"
          onClick={goBack}
          style={{
            backgroundColor: '#9dd0f1',
            height: '40px',
            width: '160px',
            fontSize: '18px',
          }}
        />
      </div>
      <div style={{ marginTop: '23px', fontSize: "18px" }}>
        <DataTable value={gameStats} responsiveLayout="scroll">
          {statType === "all" && <Column field="gamer.name" header="Játékos"></Column>}
          <Column field="gameNumber" header="Generált szám"></Column>
          <Column field="gameTime" header="Játékidő"></Column>
          <Column body={bodyTemplate} header="Játék időpontja"></Column>
        </DataTable>
      </div>
    </Card>
  );
};

export default CustomTable;
