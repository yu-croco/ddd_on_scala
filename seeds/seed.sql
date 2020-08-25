-- CLAEN UP
TRUNCATE hunters_monster_materials, monster_materials, monsters, hunters RESTART IDENTITY;

-- INSERT SEED
INSERT INTO public.monsters VALUES
    (1, 'らんぽす', 100, 10, 5),
    (2, 'ふるふる', 1500, 150, 180);

INSERT INTO public.monster_materials VALUES
    (1, 'ランポスの皮', 1, 1),
    (2, 'ランポスの爪', 1, 1),
    (3, 'アルビノ', 2, 2),
    (4, '電気袋', 2, 2);

INSERT INTO public.hunters VALUES
    (1, '新米ハンター', 1000, 100, 100);

INSERT INTO public.hunters_monster_materials VALUES
(1, 1, 3);
