var Avion = new Phaser.Class({

  Extends: Phaser.Physics.Arcade.Image,

  initialize:

  function Avion (scene, in_num, in_imagenAvionesPuntero, isCreacionAliados)
  {
      //Phaser.Physics.Arcade.Image.call(this, scene, 0, 0, 'avion');

      var x = 2000;
      var y = 2000;

      if (isCreacionAliados) {
        //this.physics.add.image(x,y,null);
        scene.physics.add.image(x,y,"avionNegro");//.setDisplayOrigin(0, 0);
        //this.physics.add.image(100,100,in_imagenAvionesPuntero);//.setDisplayOrigin(0, 0);
        this.bando = "Aliado";
        this.bullets_avion_Aliado = scene.physics.add.group({
              classType: Bullet,
              maxSize: 5,
              runChildUpdate: true
          });
        this.bullets_avion_Aliado.physicsBodyType = Phaser.Physics.ARCADE; 

      } else {
        
        scene.physics.add.image(x,y,"avionRojo");//.setDisplayOrigin(0, 0);
        //this.physics.add.image(100,100,in_imagenAvionesPuntero);//.setDisplayOrigin(0, 0);
        this.bando = "Enemigo"; 
        
        this.bullets_avion_Enemigo = scene.his.physics.add.group({
              classType: Bullet,
              maxSize: 5,
              runChildUpdate: true
          });
        this.bullets_avion_Enemigo.physicsBodyType = Phaser.Physics.ARCADE;

      }

  /*     this.setCollideWorldBounds(true);
      this.setBounce(0);
      this.scaleX=0.2;
      this.scaleY=0.2;
      this.z = 0; 
      this.vida = 100;
      this.rangoVision = rangoVisionAvion;
      this.angle = 0;
      this.tieneBomba = true;
      this.cantBombas = 1;
      this.cantCombustible = 10000;
      this.unidadDeVelocidad = 0;
      this.unidadDeConsumoCombustible = 0;
      this.isAvionEnCampoEnemigo = false;
      this.enPista = false;
      this.estadoVivaOMuerta = true;
      this.isLaAvionActiva = false;
      this.num = in_num;
      this.HayEnemigo = false;
      this.lastFiredAvion = 0;
      this.lastFiredBombaAvion = 0;
      this.physicsBodyType = Phaser.Physics.ARCADE;
      // activo, velocidad, aceleracion */
      //this.setBlendMode(1);
      //this.setDepth(1);
      //this._temp = new Phaser.Math.Vector2();
  },

  fire: function (avion)
  {
      return avion.cantCombustible = 10000+1;
  },

  update: function (time, delta)
  {

  },

  kill: function ()
  {

  }

});

