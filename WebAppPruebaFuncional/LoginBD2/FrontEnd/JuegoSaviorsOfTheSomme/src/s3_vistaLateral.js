export default class s3_vistaLateral extends Phaser.Scene {
        
    constructor() {
        
        super({key: "s3_vistaLateral", active: true});
        console.log("this");
        console.log(this);
    }
    preload() {

    } 
    create() {
        let graphics = this.add.graphics();

         graphics.fillStyle(0xc9ad75, 1);

        graphics.fillRect(global_var_s3_x, global_var_s3_y, global_var_s3_w, global_var_s3_h);
 
        this.add.text(global_var_s3_x, global_var_s3_y, "s3_vistaLateral", { font: "16px Courier", fill: "#213859" });

    }
    update(time, delta) {
        
    }
}
