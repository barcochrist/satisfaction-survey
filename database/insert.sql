INSERT INTO questions (id, title, "type", is_required) VALUES
('7059aaf7-0118-47ad-9bbd-aa21559c540a', 'Comentarios acerca de la atención recibida', 'OPEN', false),
('7059aaf7-0118-47ad-9bbd-aa21559c540b', '¿Puntos a mejorar de las instalaciones?', 'MULTIPLE_CHOICE', true),
('7059aaf7-0118-47ad-9bbd-aa21559c540c', '¿Cree que hay algo que se deba mejorar con respecto al menú?', 'OPEN', true),
('7059aaf7-0118-47ad-9bbd-aa21559c540d', '¿Que tan satisfecho estás con la atención del Mesero?', 'MULTIPLE_CHOICE', false),
('7059aaf7-0118-47ad-9bbd-aa21559c540e', '¿Recomendarías este lugar a tus amigos y familia?', 'MULTIPLE_CHOICE', true)
;

INSERT INTO question_options (id, questions_id, "name") VALUES
('7059aaf7-0118-47ad-9bbd-aa21559c541a', '7059aaf7-0118-47ad-9bbd-aa21559c540b', 'Sillas'),
('7059aaf7-0118-47ad-9bbd-aa21559c541b', '7059aaf7-0118-47ad-9bbd-aa21559c540b', 'Mesas'),
('7059aaf7-0118-47ad-9bbd-aa21559c541c', '7059aaf7-0118-47ad-9bbd-aa21559c540b', 'Zonas Verdes'),
('7059aaf7-0118-47ad-9bbd-aa21559c541d', '7059aaf7-0118-47ad-9bbd-aa21559c540d', 'Bueno'),
('7059aaf7-0118-47ad-9bbd-aa21559c541e', '7059aaf7-0118-47ad-9bbd-aa21559c540d', 'Regular'),
('7059aaf7-0118-47ad-9bbd-aa21559c541f', '7059aaf7-0118-47ad-9bbd-aa21559c540d', 'Malo'),
('7059aaf7-0118-47ad-9bbd-aa21559c541g', '7059aaf7-0118-47ad-9bbd-aa21559c540e', 'Si'),
('7059aaf7-0118-47ad-9bbd-aa21559c541h', '7059aaf7-0118-47ad-9bbd-aa21559c540e', 'No'),
('7059aaf7-0118-47ad-9bbd-aa21559c541i', '7059aaf7-0118-47ad-9bbd-aa21559c540e', 'Talvez')
;

INSERT INTO answers (id, customer_name, email) VALUES
('d6691d48-2daf-45fc-9299-f73c4f532d81', 'Usuario 1', 'usuario1@email.com'),
('834d4cfe-3c3a-45e1-bebd-96aa0cddceb2', 'Usuario 2', 'usuario2@email.com'),
('9837f194-0e27-44b4-98fd-409157ae01ec', 'Usuario 3', 'usuario3@email.com'),
('5fe9b83c-a9eb-4835-9803-729210542ebc', 'Usuario 4', 'usuario4@email.com')
;

INSERT INTO answer_questions (id, questions_id, answers_id, response) VALUES
('f68f2ff3-4e0c-4b07-9311-09ace2def8a7', '7059aaf7-0118-47ad-9bbd-aa21559c540a', 'd6691d48-2daf-45fc-9299-f73c4f532d81', 'Una muy buena atención'),
('f68f2ff3-4e0c-4b07-9311-09ace2def8a8', '7059aaf7-0118-47ad-9bbd-aa21559c540b', 'd6691d48-2daf-45fc-9299-f73c4f532d81', '7059aaf7-0118-47ad-9bbd-aa21559c541a,7059aaf7-0118-47ad-9bbd-aa21559c541b'),
('66ba129a-a524-4747-aa72-0056c3bf0c51', '7059aaf7-0118-47ad-9bbd-aa21559c540a', '834d4cfe-3c3a-45e1-bebd-96aa0cddceb2', 'Muy demorada la atención'),
('2dbf57cb-26b5-482c-a74a-a4751afebb32', '7059aaf7-0118-47ad-9bbd-aa21559c540b', '834d4cfe-3c3a-45e1-bebd-96aa0cddceb2', '7059aaf7-0118-47ad-9bbd-aa21559c541c'),
('8f7a04f6-74d8-4be0-a244-30a5d82c7862', '7059aaf7-0118-47ad-9bbd-aa21559c540a', '9837f194-0e27-44b4-98fd-409157ae01ec', 'Muy demorada la atención'),
('2f0dd624-7aa9-45f0-93c3-aff0484d9df8', '7059aaf7-0118-47ad-9bbd-aa21559c540b', '9837f194-0e27-44b4-98fd-409157ae01ec', '7059aaf7-0118-47ad-9bbd-aa21559c541c'),
('e53eaa70-3731-42a5-a86e-3357349ae6a3', '7059aaf7-0118-47ad-9bbd-aa21559c540a', '5fe9b83c-a9eb-4835-9803-729210542ebc', 'Muy demorada la atención'),
('2b3969c6-1d7f-4a3e-9220-e5cd5ae55bd0', '7059aaf7-0118-47ad-9bbd-aa21559c540b', '5fe9b83c-a9eb-4835-9803-729210542ebc', '7059aaf7-0118-47ad-9bbd-aa21559c541c'),
('acfa9836-55c3-4fa8-a989-9e2371c9df48', '7059aaf7-0118-47ad-9bbd-aa21559c540c', '5fe9b83c-a9eb-4835-9803-729210542ebc', 'No, el menú esta muy bien')
;
