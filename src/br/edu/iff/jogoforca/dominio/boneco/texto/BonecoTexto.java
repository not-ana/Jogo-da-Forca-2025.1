package br.edu.iff.jogoforca.dominio.boneco.texto;

import br.edu.iff.jogoforca.dominio.boneco.Boneco;

/**
 * Implementação do boneco para exibição em modo texto
 * Implementa padrão Singleton conforme diagrama UML
 */
public class BonecoTexto implements Boneco {
    
    // SINGLETON: única instância
    private static BonecoTexto soleInstance;
    
    private static final String[] DESENHOS = {
        // 0 - Base vazia
        "",
        
        // 1 - Orelha direita
        "quu..__",
        
        // 2 - Parte superior da cabeça
        "quu..__\n $$$b  `---.__",
        
        // 3 - Mais da cabeça
        "quu..__\n $$$b  `---.__\n  \"$$b        `--.                          ___.---uuudP",
        
        // 4 - Contorno da cabeça e rosto
        "quu..__\n $$$b  `---.__\n  \"$$b        `--.                          ___.---uuudP\n   `$$b           `.__.------.__     __.---'      $$$$\"\n     \"$b          -'            `-.-'            $$$\"",
        
        // 5 - Face e olhos
        "quu..__\n $$$b  `---.__\n  \"$$b        `--.                          ___.---uuudP\n   `$$b           `.__.------.__     __.---'      $$$$\"\n     \"$b          -'            `-.-'            $$$\"\n       \".                                       d$\"\n         `.   /                              ...\"\n           `./                           ..::-'\n            /                         .:::-'\n           :                          ::''\\\n          .' .-.             .-.           `.\n          : /'$$|           .@\"$\\           `.",
        
        // 6 - Mais do rosto
        "quu..__\n $$$b  `---.__\n  \"$$b        `--.                          ___.---uuudP\n   `$$b           `.__.------.__     __.---'      $$$$\"\n     \"$b          -'            `-.-'            $$$\"\n       \".                                       d$\"\n         `.   /                              ...\"\n           `./                           ..::-'\n            /                         .:::-'\n           :                          ::''\\\n          .' .-.             .-.           `.\n          : /'$$|           .@\"$\\           `.\n         .'|$u$$|          |$$,$$|           |\n         | `:$$:'          :$$$$$:           `.\n         :                  `\"--'             |",
        
        // 7 - Corpo superior
        "quu..__\n $$$b  `---.__\n  \"$$b        `--.                          ___.---uuudP\n   `$$b           `.__.------.__     __.---'      $$$$\"\n     \"$b          -'            `-.-'            $$$\"\n       \".                                       d$\"\n         `.   /                              ...\"\n           `./                           ..::-'\n            /                         .:::-'\n           :                          ::''\\\n          .' .-.             .-.           `.\n          : /'$$|           .@\"$\\           `.\n         .'|$u$$|          |$$,$$|           |\n         | `:$$:'          :$$$$$:           `.\n         :                  `\"--'             |\n        :##.       ==             .###.       `.\n        |##:                      :###:        |\n        |#'     `..'`..'          `###'        x:",
        
        // 8 - Corpo médio com braços aparecendo
        "quu..__\n $$$b  `---.__\n  \"$$b        `--.                          ___.---uuudP\n   `$$b           `.__.------.__     __.---'      $$$$\"\n     \"$b          -'            `-.-'            $$$\"\n       \".                                       d$\"\n         `.   /                              ...\"\n           `./                           ..::-'\n            /                         .:::-'\n           :                          ::''\\\n          .' .-.             .-.           `.\n          : /'$$|           .@\"$\\           `.\n         .'|$u$$|          |$$,$$|           |\n         | `:$$:'          :$$$$$:           `.\n         :                  `\"--'             |\n        :##.       ==             .###.       `.\n        |##:                      :###:        |\n        |#'     `..'`..'          `###'        x:\n         \\                                   xXX|\n          \\                                xXXX'|\n          /`-.                                  `.\n         :    `-  ...........,                   |\n         |         ``:::::::'       .            |\n         |             ```          |           x|\n         |                         .'    /'   xXX|",
        
        // 9 - Quase completo com pernas e patas se formando
        "quu..__\n $$$b  `---.__\n  \"$$b        `--.                          ___.---uuudP\n   `$$b           `.__.------.__     __.---'      $$$$\"\n     \"$b          -'            `-.-'            $$$\"\n       \".                                       d$\"\n         `.   /                              ...\"\n           `./                           ..::-'\n            /                         .:::-'\n           :                          ::''\\\n          .' .-.             .-.           `.\n          : /'$$|           .@\"$\\           `.\n         .'|$u$$|          |$$,$$|           |\n         | `:$$:'          :$$$$$:           `.\n         :                  `\"--'             |\n        :##.       ==             .###.       `.\n        |##:                      :###:        |\n        |#'     `..'`..'          `###'        x:\n         \\                                   xXX|\n          \\                                xXXX'|\n          /`-.                                  `.\n         :    `-  ...........,                   |\n         |         ``:::::::'       .            |\n         |             ```          |           x|\n         |                         .'    /'   xXX|\n         |    |                    ;    /:' xXXX'|\n         `.  .'                   :    /:'       |\n          |  |                   .'   /'        .'\n          `'`'                   :  ,'          |\n            |                     `'            |",
        
        // 10 - Pikachu completo (GAME OVER)
        "quu..__\n $$$b  `---.__\n  \"$$b        `--.                          ___.---uuudP\n   `$$b           `.__.------.__     __.---'      $$$$\"\n     \"$b          -'            `-.-'            $$$\"\n       \".                                       d$\"\n         `.   /                              ...\"\n           `./                           ..::-'\n            /                         .:::-'\n           :                          ::''\\\n          .' .-.             .-.           `.\n          : /'$$|           .@\"$\\           `.\n         .'|$u$$|          |$$,$$|           |\n         | `:$$:'          :$$$$$:           `.\n         :                  `\"--'             |\n        :##.       ==             .###.       `.\n        |##:                      :###:        |\n        |#'     `..'`..'          `###'        x:\n         \\                                   xXX|\n          \\                                xXXX'|\n          /`-.                                  `.\n         :    `-  ...........,                   |\n         |         ``:::::::'       .            |\n         |             ```          |           x|\n         |                         .'    /'   xXX|\n         |    |                    ;    /:' xXXX'|\n         `.  .'                   :    /:'       |\n          |  |                   .'   /'        .'\n          `'`'                   :  ,'          |\n            |                     `'            |\n             \\                                  :\n              \\                 |              .''\n               \\.               `.            /\n                /     .:::::::.. :           /\n               |     .:::::::::::`.         /\n               |   .:::------------\\       /\n              /   .''               >::'  /\n              `',:                 :    .'\n                                   `:.:'\n\n*** GAME OVER! Você deixou o Pikachu ir para forca...(seu monstro) ***"
    };
    
    /**
     * SINGLETON: Método para obter a única instância
     */
    public static BonecoTexto getSoleInstance() {
        if (soleInstance == null) {
            soleInstance = new BonecoTexto();
        }
        return soleInstance;
    }
    
    /**
     * Construtor conforme diagrama UML
     */
    public BonecoTexto() {
        // Construtor público conforme UML
    }
    
    @Override
    public void exibir(Object contexto, int partes) {
        // Para texto, o contexto pode ser null
        if (partes >= 0 && partes < DESENHOS.length) {
            System.out.println(DESENHOS[partes]);
        }
    }
}
