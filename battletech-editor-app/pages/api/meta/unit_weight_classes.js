// Returns a static list of unit weight classes

export default async function handler(req, res) {
  const weightClasses = ["Light", "Medium", "Heavy", "Assault"];
  res.status(200).json(weightClasses);
}
