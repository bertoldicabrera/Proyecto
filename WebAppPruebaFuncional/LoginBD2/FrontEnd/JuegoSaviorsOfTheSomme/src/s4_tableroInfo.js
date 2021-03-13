export default class s4_tableroInfo extends Phaser.Scene {
    constructor() {
        super({key: "s4_tableroInfo", active: true});
    }
    preload() {

    } 
    create() {
        let graphics = this.add.graphics();

        graphics.fillStyle(0xc9ad75, 1);

        graphics.fillRect(global_var_s4_x, global_var_s4_y, global_var_s4_w, global_var_s4_h);

        this.add.text(global_var_s4_x, global_var_s4_y, "s4_tableroInfo", { font: "16px Courier", fill: "#49678d" });

    }
    update(time, delta) {
        
    }
}
