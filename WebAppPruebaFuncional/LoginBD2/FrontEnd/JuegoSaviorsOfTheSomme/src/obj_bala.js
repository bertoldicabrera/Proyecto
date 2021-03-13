
var Bullet = new Phaser.Class({

    Extends: Phaser.Physics.Arcade.Image,

    initialize:

    function Bullet (scene)
    {
        Phaser.Physics.Arcade.Image.call(this, scene, 0, 0, 'bala');

        this.setBlendMode(1);
        this.setDepth(1);

       
        switch(juego_var_destinoCreacionBalas) {
            case 0:////0-torre
                this.speed = 100;
                this.lifespan = 500;
              break;
            case 1:////1-artillero
                this.speed = 100;
                this.lifespan = 250;
              break;
            case 2:////2-avion
                this.speed = 200;
                this.lifespan = 250;
                break;
            default:////otherwise
                this.speed = 80;
                this.lifespan = 1000;
          }

;

        this._temp = new Phaser.Math.Vector2();
    },

    fire: function (ship, tipo)
    {
        // this.lifespan = 1000; 

        this.setActive(true);
        this.setVisible(true);
        this.setAngle(ship.body.rotation);
        this.setPosition(ship.x, ship.y); // LE APLICO CORRIMIENTO PARA QUE NO SOLAPE/COLISIONE DE ARRANQUE
        this.body.reset(ship.x, ship.y);
        
        this.body.setSize(10, 10, true);

        var angle = Phaser.Math.DegToRad(ship.body.rotation);
        if (ship.flipX){
            angle = Phaser.Math.DegToRad(ship.body.rotation+180);
        }
        this.scene.physics.velocityFromRotation(angle, this.speed, this.body.velocity);

        this.body.velocity.x *= 2;
        this.body.velocity.y *= 2;
    },

    update: function (time, delta)
    {
         ///console.log("entro al update de bala: (time/delta"+time+" "+delta);
        //console.log("lifespan antes"+this.lifespan);
        this.lifespan -= delta;
       // console.log("lifespan despues"+this.lifespan);
        if (this.lifespan <= 0)
        {
            this.kill();
        }
    },

    kill: function ()
    {
        //console.log("entro a kill de bala");
        this.setActive(false);
        this.setVisible(false);
        this.body.stop();
        this.destroy();
    }

});