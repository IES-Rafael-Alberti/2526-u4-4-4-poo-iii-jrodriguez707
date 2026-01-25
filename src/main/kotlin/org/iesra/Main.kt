package org.iesra

enum class Direccion {
    PositiveX,
    NegativeX,
    PositiveY,
    NegativeY
}

class Robot(
    val nombre: String
) {
    var posicionX: Int = 0
    var posicionY: Int = 0
    var direccion: Direccion = Direccion.PositiveY

    init {
        require(nombre.isNotBlank())
    }

    fun mover(pasos: IntArray) {
        for (paso in pasos) {
            when (direccion) {
                Direccion.PositiveY -> posicionY += paso
                Direccion.NegativeY -> posicionY -= paso
                Direccion.PositiveX -> posicionX += paso
                Direccion.NegativeX -> posicionX -= paso
            }
            girar()
        }
    }

    private fun girar() {
        direccion = when (direccion) {
            Direccion.PositiveY -> Direccion.NegativeX
            Direccion.NegativeX -> Direccion.NegativeY
            Direccion.NegativeY -> Direccion.PositiveX
            Direccion.PositiveX -> Direccion.PositiveY
        }
    }

    fun obtenerPosicion(): Pair<Int, Int> {
        return Pair(posicionX, posicionY)
    }

    fun obtenerDireccion(): String {
        return direccion.name
    }

    override fun toString(): String {
        return "$nombre está en ($posicionX, $posicionY) ${obtenerDireccion()}"
    }
}

fun main() {

    val robot1 = Robot("R2D2")

    val movimientos = arrayOf(
        intArrayOf(1, -5, 0, -9),
        intArrayOf(3, 3, 5, 6, 1, 0, 0, -7),
        intArrayOf(2, 1, 0, -1, 1, 1, -4),
        intArrayOf(),
        intArrayOf(3, 5)
    )

    for (movimiento in movimientos) {
        robot1.mover(movimiento)
        println(robot1)
    }
}
