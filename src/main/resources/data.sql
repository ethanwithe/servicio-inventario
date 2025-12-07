INSERT INTO maquinas (
    nombre, zona, marca, modelo, estado,
    ultimo_mantenimiento, proximo_mantenimiento, fecha_adquisicion,
    descripcion, numero_serie, horas_uso, fecha_registro, fecha_actualizacion
) VALUES
-- 1
('Caminadora Pro', 'Cardio', 'LifeFitness', 'LF-2000', 'Operativa',
 '2024-11-10', '2025-05-10', '2023-02-15',
 'Caminadora profesional con inclinación automática.', 'SN-LF2000-001', 340,
 NOW(), NOW()),

-- 2
('Bicicleta Spinning X1', 'Cardio', 'Schwinn', 'SPX-100', 'Mantenimiento',
 '2024-10-20', '2025-04-20', '2023-06-01',
 'Bicicleta de spinning resistente para alto rendimiento.', 'SN-SPX100-321', 520,
 NOW(), NOW()),

-- 3
('Máquina de Pecho', 'Pesas', 'Hammer Strength', 'HS-500', 'Operativa',
 '2024-09-12', '2025-03-12', '2022-10-10',
 'Máquina para press de pecho con placas.', 'SN-HS500-778', 200,
 NOW(), NOW()),

-- 4
('Máquina de Piernas', 'Pesas', 'Technogym', 'TG-PL200', 'Fuera de Servicio',
 '2024-08-01', '2025-02-01', '2021-12-20',
 'Máquina para extensión y curl de piernas.', 'SN-TGPL200-129', 760,
 NOW(), NOW()),

-- 5
('Rack Funcional 360', 'Funcional', 'Rogue', 'RF-360', 'Operativa',
 '2024-10-15', '2025-04-15', '2023-03-23',
 'Estructura funcional para entrenamiento de fuerza y movilidad.', 'SN-RF360-555', 150,
 NOW(), NOW()),

-- 6
('Elíptica Pro X', 'Cardio', 'Precor', 'EP-450', 'Operativa',
 '2024-10-05', '2025-04-05', '2022-05-11',
 'Máquina elíptica profesional con resistencia magnética.', 'SN-EP450-676', 410,
 NOW(), NOW()),

-- 7
('Remadora Hidráulica', 'Cardio', 'Concept2', 'C2-RM100', 'Mantenimiento',
 '2024-09-18', '2025-03-18', '2022-07-08',
 'Remadora de alta precisión con resistencia hidráulica.', 'SN-RM100-002', 980,
 NOW(), NOW()),

-- 8
('Máquina de Espalda', 'Pesas', 'Matrix', 'MX-BD700', 'Operativa',
 '2024-11-01', '2025-05-01', '2023-04-01',
 'Máquina para jalones dorsales y remo sentado.', 'SN-BD700-444', 260,
 NOW(), NOW()),

-- 9
('Prensa de Piernas', 'Pesas', 'Technogym', 'TG-LP500', 'Operativa',
 '2024-08-20', '2025-02-20', '2021-09-14',
 'Prensa inclinada con guías reforzadas.', 'SN-LP500-999', 1120,
 NOW(), NOW()),

-- 10
('Máquina de Abdomen Crunch', 'Pesas', 'Hammer Strength', 'HS-AB300', 'Operativa',
 '2024-09-10', '2025-03-10', '2023-01-22',
 'Máquina para abdominales con carga ajustable.', 'SN-AB300-765', 170,
 NOW(), NOW()),

-- 11
('Estación Multifuncional', 'Funcional', 'Rogue', 'RF-MULTI700', 'Operativa',
 '2024-10-14', '2025-04-14', '2022-11-02',
 'Estación completa con barras, poleas y accesorios.', 'SN-MULTI700-321', 530,
 NOW(), NOW()),

-- 12
('Ski Erg', 'Funcional', 'Concept2', 'C2-SK500', 'Operativa',
 '2024-07-30', '2025-01-30', '2023-02-06',
 'Máquina para entrenamiento de tren superior tipo esquí.', 'SN-SK500-654', 330,
 NOW(), NOW()),

-- 13
('AirBike Pro', 'Cardio', 'Assault Fitness', 'AF-900', 'Fuera de Servicio',
 '2024-06-12', '2024-12-12', '2020-09-19',
 'AirBike con resistencia por aire para HIIT.', 'SN-AF900-909', 1580,
 NOW(), NOW()),

-- 14
('Máquina de Gemelos', 'Pesas', 'Matrix', 'MX-GL200', 'Operativa',
 '2024-08-05', '2025-02-05', '2022-03-12',
 'Máquina para elevación de talones sentado.', 'SN-GL200-122', 480,
 NOW(), NOW()),

-- 15
('Banco Plano Premium', 'Pesas', 'Rogue', 'RF-BN100', 'Operativa',
 '2024-09-28', '2025-03-28', '2023-05-15',
 'Banco plano comercial para press y ejercicios variados.', 'SN-BN100-878', 140,
 NOW(), NOW());
 
 INSERT INTO productos (
    nombre, categoria, precio, stock, estado, stock_minimo, ventas,
    descripcion, marca, proveedor, codigo_barras,
    fecha_registro, fecha_actualizacion
) VALUES
('Proteína Whey 2LB', 'Suplementos', 129.90, 25, 'Disponible', 10, 45,
 'Proteína whey premium sabor vainilla.', 'Optimum Nutrition', 'NutriSport', 'CB-PRT-001',
 NOW(), NOW()),

('Creatina Monohidratada 300g', 'Suplementos', 79.50, 8, 'Stock Bajo', 10, 20,
 'Creatina micronizada de alta pureza.', 'Universal', 'SportX', 'CB-CRT-002',
 NOW(), NOW()),

('Guantes de Gimnasio', 'Accesorios', 35.00, 0, 'Agotado', 10, 35,
 'Guantes acolchados para levantamiento de pesas.', 'Everlast', 'EquipFitness', 'CB-GNT-003',
 NOW(), NOW()),

('Rodillera Deportiva', 'Accesorios', 49.90, 12, 'Disponible', 10, 15,
 'Rodillera elástica para soporte y estabilidad.', 'Reebok', 'FitnessStore', 'CB-RDL-004',
 NOW(), NOW()),

('Aminoácidos BCAA 400g', 'Suplementos', 99.00, 5, 'Stock Bajo', 10, 10,
 'Fórmula avanzada de BCAA 2:1:1.', 'MuscleTech', 'NutriSport', 'CB-BCAA-005',
 NOW(), NOW()),

('Shaker 700ml Antifugas', 'Accesorios', 25.00, 35, 'Disponible', 10, 60,
 'Shaker resistente, tapa antifugas y mezclador interno.', 'SmartShake', 'EquipFitness', 'CB-SHK-006',
 NOW(), NOW()),

('Magnesio Líquido 250ml', 'Accesorios', 29.90, 15, 'Disponible', 10, 22,
 'Magnesio líquido para mejor agarre en pesas.', 'GorillaGrip', 'FitnessStore', 'CB-MAG-007',
 NOW(), NOW()),

('Pre-entreno Hardcore 300g', 'Suplementos', 119.00, 9, 'Stock Bajo', 10, 18,
 'Pre-entreno de alta intensidad sabor frutas.', 'C4 Energy', 'NutriSport', 'CB-PRE-008',
 NOW(), NOW()),

('Barritas Proteicas Pack 12', 'Snacks', 54.90, 28, 'Disponible', 10, 42,
 'Barritas proteicas bajas en azúcar.', 'Quest', 'SportX', 'CB-BAR-009',
 NOW(), NOW()),

('Toalla Deportiva Premium', 'Accesorios', 22.00, 7, 'Stock Bajo', 10, 12,
 'Toalla absorbente de secado rápido.', 'Nike', 'EquipFitness', 'CB-TWL-010',
 NOW(), NOW()),

('Protector Bucal Pro', 'Accesorios', 19.90, 14, 'Disponible', 10, 8,
 'Protector bucal moldeable para entrenamiento.', 'Venum', 'EquipFitness', 'CB-PBC-011',
 NOW(), NOW()),

('Termo Acero 1L', 'Accesorios', 45.00, 0, 'Agotado', 10, 30,
 'Termo térmico de acero inoxidable.', 'HydroFlask', 'FitnessStore', 'CB-TRM-012',
 NOW(), NOW()),

('Omega 3 Premium 120 cáps', 'Suplementos', 69.00, 20, 'Disponible', 10, 25,
 'Omega 3 de alta pureza con EPA y DHA.', 'Now Foods', 'NutriSport', 'CB-OM3-013',
 NOW(), NOW()),

('Kinesiotape 5m', 'Accesorios', 34.00, 16, 'Disponible', 10, 14,
 'Cinta kinesiológica elástica para soporte muscular.', 'KT Tape', 'SportX', 'CB-KT5-014',
 NOW(), NOW()),

('Multivitamínico Deportivo', 'Suplementos', 75.50, 11, 'Disponible', 10, 19,
 'Complejo multivitamínico para deportistas.', 'Animal Pak', 'NutriSport', 'CB-MVT-015',
 NOW(), NOW());

