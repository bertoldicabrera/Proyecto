export default class s2_campoEnemigo extends Phaser.Scene {
    constructor() {
        super({key: "s2_campoEnemigo", active: true});
    }
    preload() {

    } 
    create() {
        let graphics = this.add.graphics();

        graphics.fillStyle(0xb5423c, 1);

        graphics.fillRect(global_var_s2_x, global_var_s2_y, global_var_s2_w, global_var_s2_h);

        this.add.text(global_var_s2_x, global_var_s2_y, "s2_campoEnemigo", { font: "16px Courier", fill: "#ffffff" });

    }
    update(time, delta) {
        
    }
}
