.data
.align 2
global_a:
.space 4
.align 2
global_b:
.space 4
.align 2
global_x:
.space 4
.align 2
global_y:
.space 4
.align 2
global_z:
.space 4
.align 2
global_temp:
.space 4
.align 2
global_final_result:
.space 4
.align 2
global_i:
.space 4
.align 2
global_j:
.space 4
.align 2
global_sum:
.space 4
.align 2
global_arr:
.space 800
.align 2
global_c:
.space 1
.align 2
global_size:
.space 4
.align 2
global_s:
.space 4
str_572853306:
.asciiz "Tricky edge jumps test passed"

.text
empty_func:
# orig andi $sp,$sp,-4
andi $sp,$sp,-4
# Align sp to word boundary
# orig addiu $sp,$sp,-8
addiu $sp,$sp,-8
# orig sw $fp,4($sp)
sw $fp,4($sp)
# orig sw $ra,0($sp)
sw $ra,0($sp)
# orig add $fp,$sp,$zero
add $fp,$sp,$zero
# orig addiu $sp,$sp,-8
addiu $sp,$sp,-8
# orig li v0,0
li $t0,0
# orig add $v0,v0,$zero
add $v0,$t0,$zero
# orig j label_15_return_empty_func
j label_15_return_empty_func
label_15_return_empty_func:
# orig addiu $sp,$sp,8
addiu $sp,$sp,8
# orig lw $ra,0($sp)
lw $ra,0($sp)
# orig lw $fp,4($sp)
lw $fp,4($sp)
# orig addiu $sp,$sp,8
addiu $sp,$sp,8
# orig jr $ra
jr $ra

.text
tricky_func:
# orig andi $sp,$sp,-4
andi $sp,$sp,-4
# Align sp to word boundary
# orig addiu $sp,$sp,-8
addiu $sp,$sp,-8
# orig sw $fp,4($sp)
sw $fp,4($sp)
# orig sw $ra,0($sp)
sw $ra,0($sp)
# orig add $fp,$sp,$zero
add $fp,$sp,$zero
# orig addiu $sp,$sp,-16
addiu $sp,$sp,-16
# orig addiu v1,$fp,8
addiu $t0,$fp,8
# orig lw v2,0(v1)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v2,0($sp)
sw $t0,0($sp)
# orig addiu v3,$fp,12
addiu $t0,$fp,12
# orig lw v4,0(v3)
lw $t0,0($t0)
# orig lw v5,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v6,v4,$zero
add $t0,$t0,$zero
# orig sub v7,v5,v6
sub $t0,$t0,$t0
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v7,0($sp)
sw $t0,0($sp)
# orig li v8,0
li $t0,0
# orig lw v9,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v10,v8,$zero
add $t0,$t0,$zero
# orig slt v11,v10,v9
slt $t0,$t0,$t0
# orig beqz v11,label_18_if_else
beqz $t0,label_18_if_else
# orig addiu v12,$fp,8
addiu $t0,$fp,8
# orig lw v13,0(v12)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v13,0($sp)
sw $t0,0($sp)
# orig addiu v14,$fp,12
addiu $t0,$fp,12
# orig lw v15,0(v14)
lw $t0,0($t0)
# orig lw v16,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v17,v15,$zero
add $t0,$t0,$zero
# orig sub v18,v16,v17
sub $t0,$t0,$t0
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v18,0($sp)
sw $t0,0($sp)
# orig li v19,7
li $t0,7
# orig lw v20,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v21,v19,$zero
add $t0,$t0,$zero
# orig add v22,v20,v21
add $t0,$t0,$t0
# orig addiu v23,$fp,-12
addiu $t0,$fp,-12
# orig sw v22,0(v23)
sw $t0,0($t0)
# orig addiu v24,$fp,-12
addiu $t0,$fp,-12
# orig lw v25,0(v24)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v25,0($sp)
sw $t0,0($sp)
# orig li v26,123
li $t0,123
# orig lw v27,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v28,v26,$zero
add $t0,$t0,$zero
# orig mult v27,v28
mult $t0,$t0
# orig mflo v29
mflo $t0
# orig addiu v30,$fp,-16
addiu $t0,$fp,-16
# orig sw v29,0(v30)
sw $t0,0($t0)
# orig j label_19_if_end
j label_19_if_end
label_18_if_else:
# orig addiu v31,$fp,12
addiu $t0,$fp,12
# orig lw v32,0(v31)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v32,0($sp)
sw $t0,0($sp)
# orig addiu v33,$fp,8
addiu $t0,$fp,8
# orig lw v34,0(v33)
lw $t0,0($t0)
# orig lw v35,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v36,v34,$zero
add $t0,$t0,$zero
# orig sub v37,v35,v36
sub $t0,$t0,$t0
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v37,0($sp)
sw $t0,0($sp)
# orig li v38,3
li $t0,3
# orig lw v39,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v40,v38,$zero
add $t0,$t0,$zero
# orig add v41,v39,v40
add $t0,$t0,$t0
# orig addiu v42,$fp,-12
addiu $t0,$fp,-12
# orig sw v41,0(v42)
sw $t0,0($t0)
label_19_if_end:
# orig addiu v43,$fp,-12
addiu $t0,$fp,-12
# orig lw v44,0(v43)
lw $t0,0($t0)
# orig add $v0,v44,$zero
add $v0,$t0,$zero
# orig j label_17_return_tricky_func
j label_17_return_tricky_func
label_17_return_tricky_func:
# orig addiu $sp,$sp,16
addiu $sp,$sp,16
# orig lw $ra,0($sp)
lw $ra,0($sp)
# orig lw $fp,4($sp)
lw $fp,4($sp)
# orig addiu $sp,$sp,8
addiu $sp,$sp,8
# orig jr $ra
jr $ra

.text
.globl main
main:
# orig andi $sp,$sp,-4
andi $sp,$sp,-4
# Align sp to word boundary
# orig addiu $sp,$sp,-8
addiu $sp,$sp,-8
# orig sw $fp,4($sp)
sw $fp,4($sp)
# orig sw $ra,0($sp)
sw $ra,0($sp)
# orig add $fp,$sp,$zero
add $fp,$sp,$zero
# orig addiu $sp,$sp,-8
addiu $sp,$sp,-8
# orig li v45,100
li $t0,100
# orig la v46,global_x
la $t0,global_x
# orig sw v45,0(v46)
sw $t0,0($t0)
# orig li v47,150
li $t0,150
# orig la v48,global_y
la $t0,global_y
# orig sw v47,0(v48)
sw $t0,0($t0)
# orig li v49,200
li $t0,200
# orig la v50,global_z
la $t0,global_z
# orig sw v49,0(v50)
sw $t0,0($t0)
# orig jal empty_func
jal empty_func
# orig addiu $sp,$sp,0
addiu $sp,$sp,0
# orig add v51,$v0,$zero
add $t0,$v0,$zero
# orig la v52,global_temp
la $t0,global_temp
# orig sw v51,0(v52)
sw $t0,0($t0)
# orig la v53,global_y
la $t0,global_y
# orig lw v54,0(v53)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v54,0($sp)
sw $t0,0($sp)
# orig la v55,global_x
la $t0,global_x
# orig lw v56,0(v55)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v56,0($sp)
sw $t0,0($sp)
# orig jal tricky_func
jal tricky_func
# orig addiu $sp,$sp,8
addiu $sp,$sp,8
# orig add v57,$v0,$zero
add $t0,$v0,$zero
# orig la v58,global_temp
la $t0,global_temp
# orig sw v57,0(v58)
sw $t0,0($t0)
# orig li v59,0
li $t0,0
# orig la v60,global_sum
la $t0,global_sum
# orig sw v59,0(v60)
sw $t0,0($t0)
# orig li v61,0
li $t0,0
# orig la v62,global_i
la $t0,global_i
# orig sw v61,0(v62)
sw $t0,0($t0)
label_22_while_start:
# orig la v63,global_i
la $t0,global_i
# orig lw v64,0(v63)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v64,0($sp)
sw $t0,0($sp)
# orig li v65,200
li $t0,200
# orig lw v66,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v67,v65,$zero
add $t0,$t0,$zero
# orig slt v68,v66,v67
slt $t0,$t0,$t0
# orig beqz v68,label_23_while_exit
beqz $t0,label_23_while_exit
# orig la v69,global_i
la $t0,global_i
# orig lw v70,0(v69)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v70,0($sp)
sw $t0,0($sp)
# orig li v71,1
li $t0,1
# orig lw v72,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v73,v71,$zero
add $t0,$t0,$zero
# orig add v74,v72,v73
add $t0,$t0,$t0
# orig la v75,global_arr
la $t0,global_arr
# orig la v76,global_i
la $t0,global_i
# orig lw v77,0(v76)
lw $t0,0($t0)
# orig li v78,4
li $t0,4
# orig mul v79,v77,v78
mul $t0,$t0,$t0
# orig add v80,v75,v79
add $t0,$t0,$t0
# orig sw v74,0(v80)
sw $t0,0($t0)
# orig la v81,global_i
la $t0,global_i
# orig lw v82,0(v81)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v82,0($sp)
sw $t0,0($sp)
# orig li v83,1
li $t0,1
# orig lw v84,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v85,v83,$zero
add $t0,$t0,$zero
# orig add v86,v84,v85
add $t0,$t0,$t0
# orig la v87,global_i
la $t0,global_i
# orig sw v86,0(v87)
sw $t0,0($t0)
# orig j label_22_while_start
j label_22_while_start
label_23_while_exit:
# orig li v88,0
li $t0,0
# orig la v89,global_i
la $t0,global_i
# orig sw v88,0(v89)
sw $t0,0($t0)
label_24_while_start:
# orig la v90,global_i
la $t0,global_i
# orig lw v91,0(v90)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v91,0($sp)
sw $t0,0($sp)
# orig li v92,200
li $t0,200
# orig lw v93,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v94,v92,$zero
add $t0,$t0,$zero
# orig slt v95,v93,v94
slt $t0,$t0,$t0
# orig beqz v95,label_25_while_exit
beqz $t0,label_25_while_exit
# orig li v96,0
li $t0,0
# orig la v97,global_j
la $t0,global_j
# orig sw v96,0(v97)
sw $t0,0($t0)
label_26_while_start:
# orig la v98,global_j
la $t0,global_j
# orig lw v99,0(v98)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v99,0($sp)
sw $t0,0($sp)
# orig li v100,200
li $t0,200
# orig lw v101,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v102,v100,$zero
add $t0,$t0,$zero
# orig slt v103,v101,v102
slt $t0,$t0,$t0
# orig beqz v103,label_27_while_exit
beqz $t0,label_27_while_exit
# orig la v104,global_z
la $t0,global_z
# orig lw v105,0(v104)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v105,0($sp)
sw $t0,0($sp)
# orig la v106,global_arr
la $t0,global_arr
# orig la v107,global_i
la $t0,global_i
# orig lw v108,0(v107)
lw $t0,0($t0)
# orig li v109,4
li $t0,4
# orig mul v110,v108,v109
mul $t0,$t0,$t0
# orig add v111,v106,v110
add $t0,$t0,$t0
# orig lw v112,0(v111)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v112,0($sp)
sw $t0,0($sp)
# orig jal tricky_func
jal tricky_func
# orig addiu $sp,$sp,8
addiu $sp,$sp,8
# orig add v113,$v0,$zero
add $t0,$v0,$zero
# orig la v114,global_temp
la $t0,global_temp
# orig sw v113,0(v114)
sw $t0,0($t0)
# orig la v115,global_sum
la $t0,global_sum
# orig lw v116,0(v115)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v116,0($sp)
sw $t0,0($sp)
# orig la v117,global_temp
la $t0,global_temp
# orig lw v118,0(v117)
lw $t0,0($t0)
# orig lw v119,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v120,v118,$zero
add $t0,$t0,$zero
# orig add v121,v119,v120
add $t0,$t0,$t0
# orig la v122,global_sum
la $t0,global_sum
# orig sw v121,0(v122)
sw $t0,0($t0)
# orig la v123,global_j
la $t0,global_j
# orig lw v124,0(v123)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v124,0($sp)
sw $t0,0($sp)
# orig li v125,1
li $t0,1
# orig lw v126,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v127,v125,$zero
add $t0,$t0,$zero
# orig add v128,v126,v127
add $t0,$t0,$t0
# orig la v129,global_j
la $t0,global_j
# orig sw v128,0(v129)
sw $t0,0($t0)
# orig j label_26_while_start
j label_26_while_start
label_27_while_exit:
# orig la v130,global_i
la $t0,global_i
# orig lw v131,0(v130)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v131,0($sp)
sw $t0,0($sp)
# orig li v132,1
li $t0,1
# orig lw v133,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v134,v132,$zero
add $t0,$t0,$zero
# orig add v135,v133,v134
add $t0,$t0,$t0
# orig la v136,global_i
la $t0,global_i
# orig sw v135,0(v136)
sw $t0,0($t0)
# orig j label_24_while_start
j label_24_while_start
label_25_while_exit:
# orig la v137,global_sum
la $t0,global_sum
# orig lw v138,0(v137)
lw $t0,0($t0)
# orig addiu $sp,$sp,-4
addiu $sp,$sp,-4
# orig sw v138,0($sp)
sw $t0,0($sp)
# orig la v139,global_temp
la $t0,global_temp
# orig lw v140,0(v139)
lw $t0,0($t0)
# orig lw v141,0($sp)
lw $t0,0($sp)
# orig addiu $sp,$sp,4
addiu $sp,$sp,4
# orig add v142,v140,$zero
add $t0,$t0,$zero
# orig add v143,v141,v142
add $t0,$t0,$t0
# orig la v144,global_final_result
la $t0,global_final_result
# orig sw v143,0(v144)
sw $t0,0($t0)
# orig la v145,global_final_result
la $t0,global_final_result
# orig lw v146,0(v145)
lw $t0,0($t0)
# orig add $a0,v146,$zero
add $a0,$t0,$zero
# orig li $v0,1
li $v0,1
# orig syscall
syscall
# orig add v147,$zero,$zero
add $t0,$zero,$zero
# orig la v148,str_572853306
la $t0,str_572853306
# orig add $a0,v148,$zero
add $a0,$t0,$zero
# orig li $v0,4
li $v0,4
# orig syscall
syscall
# orig add v149,$zero,$zero
add $t0,$zero,$zero
# orig li v150,0
li $t0,0
# orig add $v0,v150,$zero
add $v0,$t0,$zero
# orig j label_21_return_main
j label_21_return_main
label_21_return_main:
# orig addiu $sp,$sp,8
addiu $sp,$sp,8
# orig lw $ra,0($sp)
lw $ra,0($sp)
# orig lw $fp,4($sp)
lw $fp,4($sp)
# orig addiu $sp,$sp,8
addiu $sp,$sp,8
# orig li $v0,10
li $v0,10
# orig syscall
syscall

